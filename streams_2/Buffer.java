package streams_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Buffer {
    public static void main(String[] args) {
        Leer_Fichero readText = new Leer_Fichero();

        readText.read_file();
    }
}

class Leer_Fichero {
    public void read_file() {
        try {
            FileReader input = new FileReader("streams_2/example.txt");
            BufferedReader myBuffer = new BufferedReader(input);

            String totalText = "";

            while(totalText != null) {

                totalText = myBuffer.readLine();

                if(totalText != null) {
                System.out.println(totalText);
                }
            }

            System.out.println(totalText);

            input.close();
        } catch (IOException e) {
            System.out.println("El archivo ha sido movido o no existe.\nError: " + e );
        }
    }
}