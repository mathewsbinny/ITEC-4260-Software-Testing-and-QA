import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class CarListingTest {

    private static WebDriver driver;
    private static final String CL_CAR_LISTING = "https://atlanta.craigslist.org/search/cta";
            //"https://atlanta.craiglist.org/d/cars-truck/search/cta";


    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testListing() {
        driver.get(CL_CAR_LISTING);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Find all elements with the class name "cl-search-result"
        List<WebElement> results = driver.findElements(By.className("cl-search-result"));

        // Assert that there are exactly 120 results
        Assert.assertEquals(120, results.size());

        String priceInfo = "";
        WebElement first = results.get(0);
        try {
            WebElement pi = driver.findElement(By.className("priceinfo"));
            priceInfo = pi.getText();
        } catch (Exception e) {
            priceInfo = "0";
        }
        System.out.println(priceInfo);
    }
/*

    public void testSubjectLine() {
        driver.get(CL_CAR_LISTING);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Find all elements with the class name "cl-search-result"
        List<WebElement> results = driver.findElements(By.className("label"));

        // Assert that there are exactly 120 results
        Assert.assertEquals(120, results.size());
    }

    public void testPrice() {
        driver.get(CL_CAR_LISTING);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Find all elements with the class name "cl-search-result"
        List<WebElement> results = driver.findElements(By.className("priceinfo"));

        // Assert that there are exactly 120 results
        Assert.assertEquals(120, results.size());
    }

    public void testURL() {
        driver.get(CL_CAR_LISTING);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Find all elements with the class name "cl-search-result"
        List<WebElement> results = driver.findElements(By.className("cl-search-result"));

        // Assert that there are exactly 120 results
        Assert.assertEquals(120, results.size());
    }

    public void testTimeOfRetrieval() {
        driver.get(CL_CAR_LISTING);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Find all elements with the class name "cl-search-result"
        List<WebElement> results = driver.findElements(By.className("cl-search-result"));

        // Assert that there are exactly 120 results
        Assert.assertEquals(120, results.size());
    }

 */
}
