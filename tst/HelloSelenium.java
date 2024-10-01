import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class HelloSelenium {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        //System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        //driver = new ChromeDriver();
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        driver = new EdgeDriver();
    }

    @Test
    public void testGoogle() throws Exception {
        driver.get("https://www.google.com");
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.sendKeys("GGC");
        searchBar.submit();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Thread.sleep(3000);
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertTrue(title, title.contains("GGC"));
    }


}


