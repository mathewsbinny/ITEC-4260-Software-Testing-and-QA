import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SteamJsonNode {

    private String appID;
    private JsonNode root;
    private ObjectMapper mapper = new ObjectMapper();

    public SteamJsonNode(String appID) {
        this.appID = appID;
        String url = "https://store.steampowered.com/api/appdetails?appids=" + appID + "&cc=us&l=en";
        try {
            root = mapper.readTree(new URL(url));
        } catch (Exception e) {
            System.out.println("Steam appears to be down, try again later");
            e.printStackTrace();
        }
    }

    public String getGameName() {
        String name = root.get(appID).get("data").get("name").asText();
        return name;
    }

    public String[] getGamePlatforms() {
        JsonNode platforms = root.get(appID).get("data").get("platforms");
        boolean windows = platforms.get("windows").asBoolean();
        boolean linux = platforms.get("linux").asBoolean();
        boolean mac = platforms.get("mac").asBoolean();
        ArrayList<String> platformList = new ArrayList<String>();
        if (windows) { platformList.add("windows"); }
        if (linux) { platformList.add("linux"); }
        if (mac) { platformList.add("mac"); }
        return platformList.toArray(new String[platformList.size()]);
    }

    public String getGameReleaseDate() {
        String name = root.get(appID).get("data").get("release_date").get("date").asText();
        return name;
    }

    public int getGamePrice() {
        boolean free = root.get(appID).get("data").get("is_free").asBoolean();
        if (free) {
            return 0;
        } else {
            return root.get(appID).get("data").get("price_overview").get("final").asInt();
        }
    }

    public String[] getGenres() {
        JsonNode genres = root.get(appID).get("data").get("genres");
        ArrayList<String> genresList = new ArrayList<>();
        for (JsonNode genre : genres) {
            genresList.add(genre.get("description").asText());
        }
        return genresList.toArray(new String[genresList.size()]);
    }

    public String[] getDevelopers() {
        JsonNode developers = root.get(appID).get("data").get("developers");
        ArrayList<String> devs = new ArrayList<>();
        for (int i = 0; i < developers.size(); i++) {
            devs.add(developers.get(i).asText());
        }
        return devs.toArray(new String[devs.size()]);
    }

    /*
    public String getGameRating() {
        String rating = root.get(appID).get("data").get("ratings").get("esrb").get("rating").asText();
        return rating;
    }
     */

    /*
    public int getGameRecommendationCount() {
        return root.get(appID).get("data").get("price_overview").get("final").asInt();
    }
    */
}

