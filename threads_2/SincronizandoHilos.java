package threads_2;

public class SincronizandoHilos {
    public static void main ( String[] args ) throws InterruptedException {
        HilosVarios hilo1 = new HilosVarios();
        HilosVarios1 hilo2 = new HilosVarios1(hilo1);

        hilo2.start(); // inicia el hilo
        hilo1.start(); // inicia el hilo

        System.out.println("Tasks finished");
    }
}

class HilosVarios extends Thread {
    @Override
    public void run () {
        for( int i = 0; i < 15; i++ ) {
            System.out.println( "Ejecutando hilo: " + getName() );

           /* try {
                Thread.sleep( 500 );
            } catch( InterruptedException e ) {
                throw new RuntimeException( e );
            }*/
        }
    }
}

class HilosVarios1 extends Thread  {
    private Thread hilo;
    public HilosVarios1 (Thread hilo ) {
        this.hilo = hilo;
    }

    @Override
    public void run () {
        try {
            hilo.join();
        } catch( InterruptedException e ) {
            throw new RuntimeException( e );
        }
        for( int i = 0; i < 15; i++ ) {
            System.out.println( "Ejecutando hilo: " + getName() );

           /* try {
                Thread.sleep( 500 );
            } catch( InterruptedException e ) {
                throw new RuntimeException( e );
            }*/
        }
    }
}
