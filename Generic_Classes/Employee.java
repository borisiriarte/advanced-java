package Generic_Classes;

import java.util.Date;
import java.util.GregorianCalendar;

class Employee {
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


