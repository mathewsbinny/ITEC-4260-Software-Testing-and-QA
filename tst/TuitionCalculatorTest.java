import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class TuitionCalculatorTest {

    private static final String TUITION_CALC_URL = ("https://www.usnews.com/best-colleges/georgia-gwinnett-college-41429");
    private static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.chrome.drive", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testInstateTuition() {
        //5,262 per year (two semesters) from US NEWS
        //take average of 12,13,14,15 hours
        
        driver.get(TUITION_CALC_URL);
        WebElement inStateRadio = driver.findElement(By.id("inorout1"));
        inStateRadio.click();
        WebElement creditHours = driver.findElement(By.id("creditHOURS"));
        Select creditHoursSelect = new Select(creditHours);
        creditHoursSelect.selectByVisibleText("12 hours");
        WebElement semesterCost = driver.findElement(By.id("totalcost"));
        System.out.println(semesterCost.getAttribute("value"));
    }

    @AfterClass
    public static void tearDown() {
        //Thread.sleep(6000);
        driver.quit();
    }
}
