package Generic_Classes;

public class Generic_Inheritance {
    public static void main(String[] args) {
        /*Employee Administrativa = new Employee("Carla", 5000, 1990, 1, 1);
        Admin DirectorComercial = new Admin("Juan", 6000, 1990, 1, 1);

        Employee nuevoEmpleado = DirectorComercial;*/

        Couple<Employee> Administrativa = new Couple<Employee>();

        Couple<Admin> DirectorComercial = new Couple<Admin>();

//        Couple<Employee> nuevoEmpleado = DirectorComercial;

        Couple.printWorker(Administrativa);

        Couple.printWorker(DirectorComercial);
    }
}
