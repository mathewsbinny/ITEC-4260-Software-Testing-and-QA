import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(JUnitParamsRunner.class)
public class ParametrizedTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    @Parameters({"Georgia Gwinnett College", "Georgia State University", "Kennesaw State University", "Gwinnett Technical College"})
    public void testCollegeRatings(String name) {
        driver.get("https://www.google.com");
        WebElement q = driver.findElement(By.name("q"));
        q.sendKeys(name);
        q.submit();
        WebElement tuition = driver.findElement(By.className("E0lKVc"));
        System.out.println(name + ": " + tuition.getText());
    }

    @Test
    @Parameters({"https://www.bing.com,college","https://www.google.com,university","https://duckduckgo.com,college"})
    public void testSearch(String url, String searchTerm) {
        driver.get(url);
        WebElement q = driver.findElement(By.name("q"));
        q.sendKeys(searchTerm);
        q.submit();
    }


}
