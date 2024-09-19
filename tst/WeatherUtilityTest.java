import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

public class WeatherUtilityTest {

    @Test
    public void testGetLatAndLong() {
        Location home = WeatherUtility.getLatAndLong("GA", "Lawrenceville");
        System.out.println(home.getLat());
        System.out.println(home.getLng());

        Location phoenix = WeatherUtility.getLatAndLong("AZ", "Phoenix");
        System.out.println(phoenix.getLat());
        System.out.println(phoenix.getLng());

        Location nyc = WeatherUtility.getLatAndLong("NY", "New York");
        System.out.println(nyc.getLat());
        System.out.println(nyc.getLng());
    }

    @Test(expected = NullPointerException.class)
    public void testGetLatAndLongNull() {
        Location dc = WeatherUtility.getLatAndLong("DC", "Washington DC");
        System.out.println(dc.getLat());
    }

    @Test
    public void testGetTemperature() {
        Location phoenix = WeatherUtility.getLatAndLong("GA", "Lawrenceville");
        double lat = phoenix.getLat();
        double lng = phoenix.getLng();
        double temp = WeatherUtility.getTemperature(String.valueOf(lat), String.valueOf(lng));
        Assert.assertTrue(temp > -200 && temp < 200);
        System.out.println(temp);
    }

    @Test
    public void testGetForecastURL() {
        Location phoenix = WeatherUtility.getLatAndLong("AZ", "Phoenix");
        double lat = phoenix.getLat();
        double lng = phoenix.getLng();
        URL url =
                WeatherUtility.getForecastURL(String.valueOf(lat), String.valueOf(lng));
        Assert.assertNotNull(url);
        System.out.println(url);
    }


}

