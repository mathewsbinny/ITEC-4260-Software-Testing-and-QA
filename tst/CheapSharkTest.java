import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CheapSharkTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testVeryPositive() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String url = "https://www.cheapshark.com/api/1.0/deals?storeID=1&upperPrice=15";
        JsonNode root = mapper.readTree(new URL(url));
        int count = 0;
        for (int i = 0; i < root.size(); i++) {
            JsonNode node = root.get(i);
            if (node.get("steamRatingText").asText().equals("Very Positive")) {
                count++;
            }
        }
        System.out.println(count);
        Assert.assertTrue(count > 0);
    }


    @Test
    public void countLinesTest() throws Exception {
        String url = "https://gist.githubusercontent.com/tacksoo/d1fcb51f8921cdc90d1ffadb0b63b768/raw/6c9a8b9ffadd87b4bd0217b91cdd90bb9e227ef2/schedule.csv";
        String str = IOUtils.toString(new URL(url).openStream());
        String [] lines = str.split("\n");
        Assert.assertEquals(29, lines.length);
    }


    @Test
    public void testLowestPercentage() throws Exception {
        String url = "https://gist.githubusercontent.com/tacksoo/b9edbfc8c03e1ca89d459bf1af39842d/raw/75abf553a0297d9202b1a568f185f735055d6f81/stonks.csv";
        String str = IOUtils.toString(new URL(url).openStream());
        CSVParser parser = CSVParser.parse(str, CSVFormat.DEFAULT.withHeader().builder().build());
        double min = Double.MAX_VALUE;
        for (CSVRecord record : parser) {
            String percentage = record.get(" Prediction");
            String percent = percentage.replace("%","");
            double d = Double.parseDouble(percent);
            min = Math.min(min, d);
        }
        Assert.assertFalse(min > 0);
        FileUtils.writeStringToFile(new File("stonks.csv"),min + "", StandardCharsets.UTF_8);
    }

    @Test
    public void testHeadlines() throws Exception {
        String url = "https://news.ycombinator.com";
        driver.get(url);
        List<WebElement> titles = driver.findElements(By.className("titleline"));
        Assert.assertEquals(30, titles.size());

    }
}