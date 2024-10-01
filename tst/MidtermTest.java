import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

public class MidtermTest {
    private ObjectMapper mapper = new ObjectMapper();
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        //System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        //driver = new ChromeDriver();
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        driver = new EdgeDriver();
    }

    @Test
    public void ratingExistsTest() throws IOException {
        URL url = new URL("https://www.cheapshark.com/api/1.0/deals?storeID=1&upperPrice=15");
        JsonNode root = mapper.readTree(url);
        boolean desiredRatingFound = false;
        for (JsonNode deal : root) {
            if (deal.has("steamRatingText")) {
                String steamRatingText = deal.get("steamRatingText").asText();
                if ("Very Positive".equals(steamRatingText)) {
                    desiredRatingFound = true;
                    break;
                }

            }
        }
        Assertions.assertTrue(desiredRatingFound, "At least one deal has a Very Positive rating");
    }

    @Test
    public void scheduleNumLinesTest() throws Exception {
        String str = FileUtils.readFileToString(new File("schedule.csv"), "UTF-8");
        CSVParser parser = CSVParser.parse(str, CSVFormat.DEFAULT.builder().setHeader().build());
        int lines = 1; // to account for CSV header
        boolean desiredLineNumberFound = false;
        for(CSVRecord record : parser) {
            lines ++;
            if (lines >= 29) {
                desiredLineNumberFound = true;
                System.out.println("There are 29 or more lines in this CSV file");
                break;
            }
        }
        Assertions.assertTrue(desiredLineNumberFound, "There are less than 29 lines in the CSV. The line count is: " + lines);
    }

    @Test
    public void lowestPercentageTest() throws Exception {
        String str = FileUtils.readFileToString(new File("stonks.csv"), "UTF-8");
        CSVParser parser = CSVParser.parse(str, CSVFormat.DEFAULT.builder().setHeader().build());

        double lowestValue = Double.MAX_VALUE;
        String lowestRow = null;
        for(CSVRecord record : parser) {
            String percentageString = record.get(" Prediction").replace("%", "").trim();
            double percentage = Double.parseDouble(percentageString);

            if (percentage < lowestValue) {
                lowestValue = percentage;
                lowestRow = record.toString();
            }
        }
        if (lowestRow != null) {
            File outputFile = new File("stonk.csv");
            FileUtils.writeStringToFile(outputFile, lowestRow, StandardCharsets.UTF_8);
        }

        Assertions.assertNotNull(lowestRow, "No rows were found in the CSV.");
    }


    @Test
    public void YCombinatorTest() throws Exception {
        driver.get("https://news.ycombinator.com");
        List<WebElement> headlines = driver.findElement(By.name("a"));
        Assert.assertEquals("The number of <a> elements is not 30", 30, headlines.size());

        driver.quit();

    }
}


