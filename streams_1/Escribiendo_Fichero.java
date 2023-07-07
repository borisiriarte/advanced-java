package streams_1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Escribiendo_Fichero {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Escriba lo que desee: \n");
        String textToBeWritten = input.nextLine();

        Escribiendo fileText = new Escribiendo();

        fileText.writeInFile(textToBeWritten + "\n");

    }
}

class Escribiendo{

    public void writeInFile(String text) {
        try {
            FileWriter output = new FileWriter("streams_1/example-Out.txt", true);

            for (int i = 0; i < text.length(); i++) {
                output.write(text.charAt(i));
            }

            output.close();
        } catch (IOException e) {
            System.out.println("No se ha podido realizar la impresion de los datos.\nError: " + e );
        }


    }
}
