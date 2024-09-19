import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class CSVTest {

    @Test
    public void testZipCodes() throws Exception {
        String str = FileUtils.readFileToString(new File("uszips.csv"), "UTF-8");
        CSVParser parser = CSVParser.parse(str, CSVFormat.DEFAULT.builder().setHeader().build());
        for(CSVRecord record : parser) {
            if (record.get("city").equals("Lawrenceville") && record.get("state_id").equals("GA")) {
                System.out.println(record.get("lat"));
                System.out.println(record.get("lng"));
                // 30043, 30045, 30046, 30044
            }
        }
    }

    @Test
    public void testGridPoint() throws Exception {
        // 34.00353, -84.00703
        // 	https://api.weather.gov/points/34.00353,-84.00703
        URL url = new URL("https://api.weather.gov/points/34.00353,-84.00703");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(url);
        String gridId = root.get("properties").get("gridId").asText();
        String gridX = root.get("properties").get("gridX").asText();
        String gridY = root.get("properties").get("gridY").asText();
        System.out.println(gridId + " " + gridX + " " + gridY);
    }

    @Test
    public void testWeatherData() throws Exception {
        //FFC 64 99
        //https://api.weather.gov/gridpoints/{office}/{gridX},{gridY}/forecast
        URL url = new URL("https://api.weather.gov/gridpoints/FFC/64,99/forecast");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(url);
        double temp = root.get("properties").get("periods").get(0).get("temperature").asDouble();
        System.out.println(temp);
    }

}
