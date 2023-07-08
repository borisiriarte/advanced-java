package Generic_Classes;

public class Couple <T>{
    private T first;

    public Couple() {
        this.first = null;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getFirst() {
        return first;
    }

    public static void printWorker(Couple< ? extends Employee> p ) {

        Employee primero = p.getFirst();

        System.out.println(primero);
    }
}
