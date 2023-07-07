package streams_3;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadWriteFiles {
    public static void main(String[] args) {
        try {
            int counter = 0;
            int input_data[] = new int[70506];
            boolean file_end = false;
            FileInputStream red_file = new FileInputStream("streams_3/imagen.jpg");

            while(!file_end ) {
                int byte_input = red_file.read();

                if (byte_input == -1) {
                    file_end = true;
                } else {
                    input_data[counter] = byte_input;
                    counter++;
                }

            }

            System.out.println(counter);

            create_file(input_data);

            red_file.close();
        } catch (IOException e) {
            System.out.println("The file is missing. Error: " + e);
        }
    }

    static void create_file(int data_new_file[]) {

        try {
            FileOutputStream new_file = new FileOutputStream("streams_3/imagen_copia.jpg");

            for (int data: data_new_file) {
                new_file.write(data);
            }

            new_file.close();

        } catch (IOException e) {
            System.out.println("The directory is missing. Error: " + e);
        }
    }
}


