import org.junit.Assert;
import org.junit.Test;

public class StudentTest {

    @Test
    public void testStudentInitialGPA() {
        Student demo = new Student("Demo Student", 1);
        Assert.assertEquals("A new student object always has an expected GPA of zero",0, demo.getGPA(),0.001); //0.001 is the delta of tolerance accepted in that value to be considered the same
    }
}
