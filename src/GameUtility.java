import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GameUtility {

    private static ObjectMapper mapper = new ObjectMapper();

    private static URL getGameURL(String appID) {
        String url = "https://store.steampowered.com/api/appdetails?appids=" + appID + "&cc=us&l=en";
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getGameName(String appID) {
        URL url = getGameURL(appID);
        JsonNode root = null;
        try {
            root = mapper.readTree(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String name = root.get(appID).get("data").get("name").asText();
        return name;
    }

    public static String[] getGamePlatforms(String appID) {
        URL url = getGameURL(appID);
        JsonNode root = null;
        try {
            root = mapper.readTree(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static String getGameReleaseDate(String appID) {
        URL url = getGameURL(appID);
        JsonNode root = null;
        try {
            root = mapper.readTree(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String name = root.get(appID).get("data").get("release_date").get("date").asText();
        return name;
    }

    public static int getGamePrice(String appID) {
        URL url = getGameURL(appID);
        JsonNode root = null;
        try {
            root = mapper.readTree(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean free = root.get(appID).get("data").get("is_free").asBoolean();
        if (free) {
            return 0;
        } else {
            return root.get(appID).get("data").get("price_overview").get("final").asInt();
        }
    }

    public static String[] getGenres(String appID) {
        URL url = getGameURL(appID);
        JsonNode root = null;
        try {
            root = mapper.readTree(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonNode genres = root.get(appID).get("data").get("genres");
        ArrayList<String> genresList = new ArrayList<>();
        for (JsonNode genre : genres) {
            genresList.add(genre.get("description").asText());
        }
        return genresList.toArray(new String[genresList.size()]);
    }

    public static String[] getDevelopers(String appID) {
        URL url = getGameURL(appID);
        JsonNode root = null;
        try {
            root = mapper.readTree(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonNode developers = root.get(appID).get("data").get("developers");
        ArrayList<String> devs = new ArrayList<>();
        for (int i = 0; i < developers.size(); i++) {
            devs.add(developers.get(i).asText());
        }
        return devs.toArray(new String[devs.size()]);
    }

    //TODO: implement getRating() to retrieve ESRB rating
    //if ESRB rating does not exist, return the string "none"
    public String getRating(String appID) {
        URL url = getGameURL(appID);
        JsonNode root = null;
        try {
            root = mapper.readTree(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonNode rating = root.get(appID).get("data").get("ratings").get("esrb").get("rating");

        if (rating != null) {
            return rating.get("rating").asText();
        } else {
            return "none";
        }
    }
}
