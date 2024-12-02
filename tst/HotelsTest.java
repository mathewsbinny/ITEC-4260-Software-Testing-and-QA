import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class HotelsTest {
    /*
       hotels
Park Hyatt

cities
Chicago
New York City
Washington DC
San Diego
Los Angeles

table schema
id, hotel name, city, price, url, month, day, year, time of retrievel

expedia.com - bot detection errors
google travel
hotwire.com
booking.com
priceline.com - very sensitive bot detection



https://www.expedia.com/Hotel-Search?destination=Park%20Hyatt%20New%20York%2C%20New%20York%2C%20New%20York%2C%20United%20States%20of%20America&regionId=2621&latLong=40.765327%2C-73.978928&selected=7650971&flexibility=0_DAY&d1=2024-10-29&startDate=2024-10-29&d2=2024-10-30&endDate=2024-10-30&adults=2&rooms=1&theme=&userIntent=&semdtl=&useRewards=false&sort=RECOMMENDED


     */
    private static WebDriver driver;
    private static String TRAVEL_URL = "https://www.google.com/travel/";
    private static String[] HOTELS = {"Park Hyatt"};
    private static String[] CITIES = {"Los Angeles"};

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.edge.driver","msedgedriver.exe");
        driver = new EdgeDriver();
    }

    @Test
    public void testHotel() throws InterruptedException {
        driver.get(TRAVEL_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // very difficult for most websites
        WebElement inputHotel = driver.findElement(By.cssSelector("#yDmH0d > c-wiz.zQTmif.SSPGKf > div > div.lteUWc > div > c-wiz > div > div > div.mNENNb > div.dUSklb > div.vBL51c > div > div > div > div.cQnuXe.k0gFV > div > div > div.V00Bye.ESCxub > input"));
        inputHotel.sendKeys(HOTELS[0] + " " + CITIES[0]);
        inputHotel.sendKeys(Keys.RETURN);
        Thread.sleep(2000);
        inputHotel.click();
        inputHotel.sendKeys(Keys.DOWN);
        //WebElement submitButton = driver.findElement(By.cssSelector("button.btn-primary:nth-child(1)"));
        //submitButton.click();
    }

}

