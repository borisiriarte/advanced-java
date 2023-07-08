package serializacion;

import java.io.*;
import java.util.Date;
import java.util.GregorianCalendar;

public class Serializando {
    public static void main(String[] args) {
        Admin boss = new Admin("Boris", 50000, 1990, 1, 1);

        boss.setIncentive(5000);

        Employee[] employees = new Employee[3];
        employees[0] = boss;
        employees[1] = new Employee("John", 5000, 1990, 1, 1);
        employees[2] = new Employee("Mary", 6000, 1990, 1, 1);

        try {
            ObjectOutputStream write_file =
                    new ObjectOutputStream(new FileOutputStream("serializacion/employees.dat"));

            write_file.writeObject(employees);

            write_file.close();


            ObjectInputStream read_file =
                    new ObjectInputStream(new FileInputStream("serializacion/employees.dat"));


            Employee[] employees_read = (Employee[]) read_file.readObject();

            for (Employee employee : employees_read) {
                System.out.println(employee);
            }

            read_file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private double salary;
    private final Date contractYear;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.contractYear = new GregorianCalendar(year, month -1 , day).getTime();
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Date getContractYear() {
        return contractYear;
    }

    public void increase_salary(double percentage) {
        double increase = this.salary * percentage /100;

        this.salary += increase;
    }

    @Override
    public String toString() {
        return "--employee--" +
                "\nname: " + name +
                "\nsalary: " + salary +
                "\ncontractYear: " + contractYear + "\n";
    }
}

//_---------------________-_--_____--------_____----____---------_____-----

class Admin extends Employee {
    @Serial
    private static final long serialVersionUID = 1L;
    private double incentive;

    public Admin(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        this.incentive = 0;
    }

    @Override
    public double getSalary() {
        double baseSalary = super.getSalary();

        return baseSalary + incentive;
    }



    public double getIncentive() {
        return incentive;
    }

    public void setIncentive(double incentive) {
        this.incentive = incentive;
    }

    @Override
    public String toString() {
        return  super.toString() + "--Boss--" +
                "\nincentive: " + incentive + "\n";
    }
}