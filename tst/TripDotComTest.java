import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverInfo;

public class TripDotComTest {

    private static WebDriver driver;
    private String TRAVEL_WEBSITE_URL = "https://us.trip.com/";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("user-data-dir=C:\\Users\\tim\\AppData\\Local\\Microsoft\\Edge\\User Data\\Default");
        driver = new EdgeDriver(options);
    }

    @Test
    public void testHotel() throws InterruptedException {
        driver.get(TRAVEL_WEBSITE_URL);
        WebElement destination = driver.findElement(By.id("destinationInput"));
        destination.sendKeys("New York City Holiday Inn");
        Thread.sleep(3000);
        destination.sendKeys(Keys.DOWN);
        destination.sendKeys(Keys.ENTER);
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"searchBoxCon\"]/div/div/div/div/div[4]/button/div/span[2]"));
        searchButton.click();

        //asks me to register to continue :(
    }
}
