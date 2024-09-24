import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void testRandomString() {
        String token = RandomStringUtils.randomAlphanumeric(20).toUpperCase();
        System.out.println(token);
        int d = StringUtils.getLevenshteinDistance("ITEC4260", "CHEM2122K");
        System.out.println(d);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidate() {
        Student drim = new Student("drim", 42);
        drim.enrollKinder(40);

    }
}

