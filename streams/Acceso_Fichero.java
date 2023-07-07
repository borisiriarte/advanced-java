package streams;

import java.io.FileReader;
import java.io.IOException;

public class Acceso_Fichero {

    public static void main(String[] args) {
        //streams or data flow
        Leer_Fichero accessed = new Leer_Fichero();


        accessed.read_file();
    }
}

class Leer_Fichero {
    public void read_file() {
        try {
            FileReader input = new FileReader("streams/example.txt");

            int redFile = 0;

            StringBuilder fullText = new StringBuilder();

            while(redFile != -1) {
                redFile = input.read();

                char letter = (char) redFile;

                fullText.append(letter);
            }

            System.out.println(fullText);

            input.close();
        } catch (IOException e) {
            System.out.println("El archivo ha sido movido o no existe.\nError: " + e );
        }
    }
}
