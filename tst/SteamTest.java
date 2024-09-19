import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

public class SteamTest {
    private ObjectMapper mapper = new ObjectMapper();
    private String [] appIDs = {"1604030","594650","1245620","1384160","239140"};

    @Test
    public void testGameTitle() throws IOException {
        //jackson databind
        for (int i = 0; i < appIDs.length; i++) {
            URL url = new URL("https://store.steampowered.com/api/appdetails?appids=" + appIDs[i] +"&cc=us&l=en");
            JsonNode root = mapper.readTree(url);
            String name = root.get(appIDs[i]).get("data").get("name").asText();
            System.out.println(name);
        }
    }
    @Test
    public void testGamePrice() throws IOException {
        for (int i = 0; i < appIDs.length; i++) {
            URL url = new URL("https://store.steampowered.com/api/appdetails?appids=" + appIDs[i] +"&cc=us&l=en");
            JsonNode root = mapper.readTree(url);
            int price = root.get(appIDs[i]).get("data").get("price_overview").get("final").asInt();
            System.out.println(price);
        }
    }
    @Test
    public void testGameGenres() throws IOException {
        for (int i = 0; i < appIDs.length; i++) {
            URL url = new URL("https://store.steampowered.com/api/appdetails?appids=" + appIDs[i] +"&cc=us&l=en");
            JsonNode root = mapper.readTree(url);
            JsonNode genres = root.get(appIDs[i]).get("data").get("genres");
            for (int j = 0; j < genres.size(); j++) {
                System.out.println(genres.get(j).get("description").asText() + " ");
            }
            System.out.println();
        }
    }
}
