package Generic_Classes;

public class MetodosGenericos {
    public static void main(String[] args) {
        String[] names = {"Juan", "Pedro", "Maria", "Ana"};

        /*String elementos = MyMatrix.<String>getElements(names);*/

        System.out.println(MyMatrix.getMenor(names));
    }
}

class MyMatrix{

    public static <T extends Comparable> T getMenor(T[] a){
        if(a == null || a.length == 0) {
            return null;
        }

        T elementoMenor = a[0];

        for (int i = 1; i < a.length; i++) {
            if(elementoMenor.compareTo(a[i]) > 0) {
                elementoMenor = a[i];
            }
        }

        return elementoMenor;
    }
}
