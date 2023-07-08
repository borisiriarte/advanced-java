package Generic_Classes;

public class MetodosGenericos {
    public static void main(String[] args) {
        String[] names = {"Juan", "Pedro", "Maria"};

        String elementos = MyMatrix.<String>getElements(names);

        System.out.println(elementos);
    }
}

class MyMatrix{

    public static <T> String getElements(T[] a) {
        return "El array tiene " + a.length + " elementos";
    }
}
