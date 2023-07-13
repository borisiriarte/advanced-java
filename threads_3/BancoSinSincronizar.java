package threads_3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BancoSinSincronizar {
    public static void main ( String[] args ) {
         Banco banco = new Banco ( );
        for( int i = 0; i < 100; i++ ) {
            EjecucionTransferencias transferencia = new EjecucionTransferencias ( banco, i, 2000 );

            Thread hilo = new Thread(transferencia);

            hilo.start();
        }
    }
}

class Banco {
    private final double[] cuentas;
    //private Lock cierreBanco = new ReentrantLock();
    //private Condition saldoSuficiente;

    public Banco () {
        this.cuentas = new double[100];
        for( int i = 0; i < cuentas.length; i++ ) {
              cuentas[i] = 2000;
        }
        //saldoSuficiente = cierreBanco.newCondition();
    }

    public synchronized void transferencia ( int cuentaOrigen, int cuentaDestino, double monto ) throws InterruptedException {
        //cierreBanco.lock(); // Bloquea el acceso al banco para evitar que se realicen operaciones simultaneas
        //try {
            while(cuentas[cuentaOrigen] < monto){
                //saldoSuficiente.await();
                wait();
            } // Evalua si la cuenta origen tiene el dinero suficiente para realizar la transferencia

            System.out.println(Thread.currentThread());
            cuentas[cuentaOrigen] -= monto; //Retiro de la cuenta origen
            System.out.printf( "%10.2f de %d a %d", monto, cuentaOrigen, cuentaDestino );
            cuentas[cuentaDestino] += monto; //Deposito de la cuenta destino
            System.out.printf( " --Saldo total: %10.2f%n", getSaldo() );
            //saldoSuficiente.signalAll(); //Notifica a las cuentas que ya se pueden realizar operaciones
        //} finally {
            //cierreBanco.unlock();
        //}

        notifyAll();
    }
    public double getSaldo () {
        double total = 0;
        for( double cuenta : cuentas ) {
            total += cuenta;
        }
        return total;
    }
}

class EjecucionTransferencias implements Runnable {
    private Banco banco;
    private int iFrom;
    private double montoMax;
    public EjecucionTransferencias ( Banco b, int iFrom, double montoMax ) {
        this.banco = b;
        this.iFrom = iFrom;
        this.montoMax = montoMax;
    }

    @Override
    public void run () {
        try {
            while(true) {
                int iTo = ( int ) ( 100 * Math.random() );
                double monto = montoMax * Math.random();
                banco.transferencia( iFrom, iTo, monto );
                Thread.sleep( ( int ) ( Math.random() * 100 ) );

                if( banco.getSaldo() < 0 ) {
                    System.out.println( "Negative balance" );
                }
            }
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }
}

