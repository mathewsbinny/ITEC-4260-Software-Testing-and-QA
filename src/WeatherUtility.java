import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

public class WeatherUtility {

    public static final String WEATHER_URL_PREFIX
            = "https://api.weather.gov/points/";


    public static double getTemperature(String lat, String lng) {
        double temp = Double.MAX_VALUE;
        try {
            URL url = getForecastURL(lat, lng);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(url);
            //path to temperature is: properties.periods[0].temperature
            temp =
                    root.get("properties").get("periods").get(0).get("temperature").asDouble();
        } catch (Exception e ) {
            throw new RuntimeException(e);
        }
        return temp;
    }

    public static URL getForecastURL(String lat, String lng) {
        //https://api.weather.gov/points/{latitude},{longitude}
        String url = WEATHER_URL_PREFIX + lat + "," + lng;
        String forecastURLStr = "";
        URL forecastURL = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(new URL(url));
            forecastURLStr = root.get("properties").get("forecast").asText();
            forecastURL = new URL(forecastURLStr);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return forecastURL;
    }

    public static Location getLatAndLong(String stateId, String city) {
        Location loc = null;
        try {
            String str = FileUtils.readFileToString(new File("uszips.csv"), "UTF-8");
            CSVParser parser = CSVParser.parse(str, CSVFormat.DEFAULT.builder().setHeader().build());
            for(CSVRecord record : parser) {
                if (record.get("city").equals(city) && record.get("state_id").equals(stateId)) {
                    double lat = Double.parseDouble(record.get("lat"));
                    double lng = Double.parseDouble(record.get("lng"));
                    loc = new Location(lat, lng);
                    //returns the last entry if
                    // multiple zipcodes exists for city
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new RuntimeException("Ooops something went wrong", e);
        }
        return loc;
    }



}

