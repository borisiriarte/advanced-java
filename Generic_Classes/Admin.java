package Generic_Classes;

class Admin extends Employee{
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