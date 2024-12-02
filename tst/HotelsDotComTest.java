import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RunWith(JUnitParamsRunner.class)
public class HotelsDotComTest {
    private static WebDriver driver;
    private String TRAVEL_WEBSITE_URL = "https://www.hotels.com/";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("user-data-dir=C:\\Users\\tim\\AppData\\Local\\Microsoft\\Edge\\User Data\\Default");
        driver = new EdgeDriver(options);
    }

    @Test
    @Parameters(value={ "New York City", "Los Angeles", "Las Vegas", "San Diego", "Chicago"})
    public void testHotel(String city) throws InterruptedException {
        // [destination] => Panama City, Panamá Province, Panama [flexibility] => 0_DAY [d1] => 2024-11-08 [startDate] => 2024-11-08 [d2] => 2024-11-10
        // [endDate] => 2024-11-10 [adults] => 2 [rooms] => 1

        String[] hotPlaces = { "Holiday Inn", "Best Western", "Doubletree", "Courtyard", "Hampton Inn"};
        String PREPARED_URL = "Hotel-Search?destination=" + city +
                "&startDate=2024-11-23" + "&endDate=2024-11-24" + "&adults=2" +
                "&rooms=1" + "&hotelName=" + hotPlaces[2];
        driver.get(TRAVEL_WEBSITE_URL + PREPARED_URL);

        List<WebElement> entries = driver.findElements(By.id("listing-content-entry"));
        entries.get(0).click();

        String regex = "\\$[0-9,]+ total";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(driver.getPageSource());
        matcher.find();
        System.out.println(matcher.group());

    }
}
