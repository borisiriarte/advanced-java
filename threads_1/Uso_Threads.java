package threads_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Uso_Threads {
    public static void main(String[] args) {
        JFrame marco = new MarcoRebote();
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setVisible(true);
    }
}

//1.- CLASE QUE IMPLEMENTA LA INTERFAZ RUNABLE
class PelotaHilos implements Runnable {
    private Pelota pelota;
    private Component componente;

    public PelotaHilos(Pelota pelota, Component componente) {
        this.pelota= pelota;
        this.componente= componente;
    }

    //2.- MÉTODO RUN Y METEMOS EL FOR, PARA QUE PUEDAN HABER VARIAS PELOTAS SIMULTANEAS
    //RUN() PERMITE HACER TAREAS SIMULTÁNEAS.
    public void run() {

        for (int i=1; i<=3000; i++){
            //while(!Thread.interrupted()) {
            // while(!Thread.currentThread().isInterrupted()) {
            pelota.mueve_pelota(componente.getBounds());
            componente.paint(componente.getGraphics());

            try {
                //HACEMOS UNA PAUSA DURANTE LA EJECUCIÓN DEL PROGRAMA
                Thread.sleep(9);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
                //System.out.println("Hilo Bloqueado. Imposible su interrupción"); // lo usamos con t.interrupt(); que está en la línea 245
                //System.exit(0);   //detenemos la ejecución.

                //PROGRAMAMOS UNA INTERRUPCIÓN DEL ACTUAL HILO
                Thread.currentThread().interrupt();
            }

        }
        System.out.println("Estado del hilo al terminar " + Thread.currentThread().isInterrupted());
    }
}

//CLASE QUE SE ENCARGA DEL MOVIMIENTO DE LA PELOTA
//TAMBN  SE ENCARGA DE QUE LA PELOTA REBOTE EN LOS BORDES
class Pelota{

    // MÉTODO MUEVE LA PELOTA INVIRTIENDO LA  POSICIÓN SI CHOCA CON LOS LÍMITES.
    // EL PARÁMETRO RECTANGLE2D, RECIBE LOS LÍMITES DE LA LÁMINA, PAR QUE LA PELOTA REBOTE EN LOS BORDES.
    public void mueve_pelota(Rectangle2D limites){

        //INCREMENTAMOS LAS COORDENADAS (X,Y), PARA QUE LA PELOTA SE MUEVA.
        x+=dx;
        y+=dy;

        //DETECTAMOS EL PUNTO MINIMO CON GETMAXX() E INVERTIMOS LA DIRECCIÓN DE LA PELOTA.
        if(x<limites.getMinX()){
            x=limites.getMinX();
            dx=-dx;
        }

        if(x + TAMX>=limites.getMaxX()){

            x=limites.getMaxX() - TAMX;

            dx=-dx;
        }

        if(y<limites.getMinY()){

            y=limites.getMinY();

            dy=-dy;
        }

        if(y + TAMY>=limites.getMaxY()){

            y=limites.getMaxY()-TAMY;

            dy=-dy;

        }

    }

    //Forma de la pelota en su posición inicial

    public Ellipse2D getShape(){

        return new Ellipse2D.Double(x,y,TAMX,TAMY);

    }

    private static final int TAMX=15;

    private static final int TAMY=15;

    private double x=0;

    private double y=0;

    private double dx=1;

    private double dy=1;


}

//Lámina que dibuja las pelotas----------------------------------------------------------------------


class LaminaPelota extends JPanel{

    //Añadimos pelota a la lámina
    public void add(Pelota b){

        pelotas.add(b);
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        for(Pelota b : pelotas){

            g2.fill(b.getShape());
        }

    }

    private ArrayList<Pelota> pelotas = new ArrayList<Pelota>();
}


//Marco con lámina y botones------------------------------------------------------------------------------

class MarcoRebote extends JFrame{

    public MarcoRebote(){

        setBounds(600,300,600,350);

        setTitle ("Rebotes");

        lamina = new LaminaPelota();

        add(lamina, BorderLayout.CENTER);

        JPanel laminaBotones=new JPanel();

        /*ponerBoton(laminaBotones, "Dale!", new ActionListener(){

            public void actionPerformed(ActionEvent evento){
                comienza_el_juego();
            }
        });

        ponerBoton(laminaBotones, "Salir", new ActionListener(){

            public void actionPerformed(ActionEvent evento){
                System.exit(0);
            }

        });

        ponerBoton(laminaBotones, "Detener", new ActionListener(){

            public void actionPerformed(ActionEvent evento){
                detener();
            }

        });
*/

        bootstrap1 = new JButton("Hilo 1");
        bootstrap1.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                comienza_el_juego(e);
            }
        });

        bootstrap2 = new JButton("Hilo 2");
        bootstrap2.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                comienza_el_juego(e);
            }
        });

        bootstrap3 = new JButton("Hilo 3");
        bootstrap3.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                comienza_el_juego(e);
            }
        });

        stop1 = new JButton("Detener");
        stop1.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                detener(e);
            }
        });

        stop2 = new JButton("Detener");
        stop2.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                detener(e);
            }
        });

        stop3 = new JButton("Detener");
        stop3.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                detener(e);
            }
        });


        laminaBotones.add( stop1);
        laminaBotones.add( stop2);
        laminaBotones.add( stop3);
        laminaBotones.add( bootstrap1 );
        laminaBotones.add( bootstrap2 );
        laminaBotones.add( bootstrap3 );
        add(laminaBotones, BorderLayout.SOUTH);
    }


    //Ponemos botones

    public void ponerBoton(Container c, String titulo, ActionListener oyente){

        JButton boton=new JButton(titulo);

        c.add(boton);

        boton.addActionListener(oyente);

    }

    //Añade pelota y la bota 1000 veces

    Thread t1, t2, t3;

    JButton bootstrap1, bootstrap2, bootstrap3, stop1, stop2, stop3;
    private final LaminaPelota lamina;

    public void comienza_el_juego ( ActionEvent e){


        Pelota pelota = new Pelota();

        lamina.add(pelota);

        //3.-CREAMOS UNA INSTANCIA DE LA CLASE QUE IMPLEMENTA LA INTERFAZ RUNNABLE, PELOTAHILOS, Y LA
        //..ALMACENAMOS EN UNA VARIABLE DE TIPO RUNNABLE.
        Runnable r = new PelotaHilos(pelota,lamina);

        //4.-CREAMOS UNA TAREA...ESTE CONSTRUCTOR DE THREAD, PERMITE PASAR UN OBJ DE TIPO RUNNABLE
        if(e.getSource().equals( bootstrap1 )){
            t1 = new Thread(r);
            t1.start();
        } else if(e.getSource().equals( bootstrap2 )){
            t2 = new Thread(r);
            t2.start();
        } else if(e.getSource().equals( bootstrap3)){
            t3 = new Thread(r);
            t3.start();
        }

        //5.-LE DECIMOS QUR COMIENCE LA TAREA

    }

    public void detener(ActionEvent e) {
        //t.stop();  //XXX está obsoleto
        if(e.getSource().equals( stop1 )){
            t1.interrupt();
        } else if(e.getSource().equals( stop2 )){
            t2.interrupt();
        } else if(e.getSource().equals( stop3 )){
            t3.interrupt();
        }
    }
}

