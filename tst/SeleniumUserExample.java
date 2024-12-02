import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumUserExample {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:\\Users\\tim\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testHotwire() throws Exception {
        driver.get("https://www.hotwire.com");
        WebElement input = driver.findElement(By.cssSelector("#root > div.page-home > div.hero-background-container.hero-background-container--campaign > div.hero-background > div > div > div.farefinder-container > div > div.farefinder-hotel.farefinder-content > form > div.farefinder-hotel-horizontal__row1 > div > div > div.hw-form-group.form-group.floating-label.empty.has-icon > input"));
        input.sendKeys("New York City");
        WebElement submit = driver.findElement(By.className("btn__label"));
        submit.click();
    }


}
