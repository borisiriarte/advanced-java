import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Acceso_Fichero {

    public static void main(String[] args) {
        //streams or data flow

    }
}

class Leer_Fichero {
    public void read() {
        try {
            FileReader input = new FileReader("/example.txt");
        } catch (FileNotFoundException e) {
            System.out.println("El archivo ha sido movido o no existe. Error: " + e );
        }
    }
}
