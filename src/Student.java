public class Student {
    private String name;
    private int id;
    private double gpa;

    Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.gpa = 0;
    }

    public double getGPA() {
        return gpa;
    }
}
