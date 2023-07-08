package directory_file;

import java.io.File;

public class Test_Routes {
    public static void main(String[] args) {
        File file = new File("file_example");

        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());
    }
}
