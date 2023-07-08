package Generic_Classes;

public class Use_Couple {

    public static void main(String[] args) {
        Couple<String> couple = new Couple<>();

        couple.setFirst("Boris");

        System.out.println(couple.getFirst());

        Person person = new Person("Anna");

        Couple<Person> couple2 = new Couple<>();

        couple2.setFirst(person);

        System.out.println(couple2.getFirst().toString());
    }
}

class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "--Person--" +
                "\nname: " + name ;
    }
}
