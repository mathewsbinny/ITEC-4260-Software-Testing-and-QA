import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class TuitionCalculatorTest {

    private static WebDriver driver;
    private final String TUITION_CALC_URL = "https://www.ggc.edu/admissions/tuition-and-financial-aid-calculators";

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    private static int getSemesterCostPerHour(String hours) {
        WebElement creditHours = driver.findElement(By.id("creditHOURS"));
        Select creditHoursSelect = new Select(creditHours);
        creditHoursSelect.selectByVisibleText(hours);
        WebElement semesterCost = driver.findElement(By.id("totalcost"));
        String cost = semesterCost.getAttribute("value");
        return Integer.parseInt(cost.substring(1,cost.length()-3));
    }

    @Test
    public void testInstateTuition() {
        // 5,262 per year (two semesters) from USNEWS
        // take the average of 12, 13, 14, and 15 hours

        driver.get(TUITION_CALC_URL);
        WebElement inStateRadio = driver.findElement(By.id("inorout1"));
        inStateRadio.click();
        int twelveHoursCost = getSemesterCostPerHour("12 hours");
        int thirteenHoursCost = getSemesterCostPerHour("13 hours");
        int fourteenHoursCost = getSemesterCostPerHour("14 hours");
        int fifteenHoursCost = getSemesterCostPerHour("15 hours");
        double average = (twelveHoursCost + thirteenHoursCost + fourteenHoursCost + fifteenHoursCost)/4.0;
        System.out.println(average);
        double expected = 5262.0;
        Assert.assertEquals(expected, average*2, 350);

    }

    @Test
    public void testOutOfStateTuition() {
        driver.get(TUITION_CALC_URL);
        WebElement outStateRadio = driver.findElement(By.id("inorout0"));
        outStateRadio.click();
        int twelveHoursCost = getSemesterCostPerHour("12 hours");
        int thirteenHoursCost = getSemesterCostPerHour("13 hours");
        int fourteenHoursCost = getSemesterCostPerHour("14 hours");
        int fifteenHoursCost = getSemesterCostPerHour("15 hours");
        double average = (twelveHoursCost + thirteenHoursCost + fourteenHoursCost + fifteenHoursCost)/4.0;
        System.out.println(average);
        double expected = 16348.0;
        Assert.assertEquals(expected, average*2, 1000);
    }

    @Test
    public void testFall2024TuitionBill() {
        //$2000
        driver.get(TUITION_CALC_URL);
        WebElement inStateRadio = driver.findElement(By.id("inorout1"));
        inStateRadio.click();
        int twelveHoursCost = getSemesterCostPerHour("12 hours");
        double estimatedCost = twelveHoursCost;
        System.out.println(estimatedCost);
        double expected = 2388.33;
        Assert.assertEquals(expected, estimatedCost, 500);
    }


    @AfterClass
    public static void tearDown() throws InterruptedException {
        Thread.sleep(6000);
        driver.close();
    }

}