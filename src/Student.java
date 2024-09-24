import org.apache.commons.lang3.Validate;

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

    public void enrollKinder(int age) {
        Validate.isTrue(age >= 0 && age <= 5, "Too old for Kindergarten");
    }
}
