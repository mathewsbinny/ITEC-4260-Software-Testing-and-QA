import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Objects;

public class Game {
    private String appID;
    private String name;
    private String[] platforms;
    private String releaseDate;
    private String[] developers;
    private String[] genres;
    private int price;
    private String rating;

    public Game(String appID) {
        this.appID = appID;
        SteamJsonNode root = new SteamJsonNode(appID);
        this.name = root.getGameName();
        this.releaseDate = root.getGameReleaseDate();
        this.platforms = root.getGamePlatforms();
        this.developers = root.getDevelopers();
        this.genres = root.getGenres();
        this.price = root.getGamePrice();
        this.rating = root.getGameRating();
    }

    public Game(String appID, String name, String[] platforms, String releaseDate, String[] developers, String[] genres, int price, String rating) {
        this.appID = appID;
        this.name = name;
        this.platforms = platforms;
        this.releaseDate = releaseDate;
        this.developers = developers;
        this.genres = genres;
        this.price = price;
        this.rating = rating;
    }

    public String getAppID() {
        return appID;
    }

    public String getName() {
        return name;
    }

    public String[] getPlatforms() {
        return platforms;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String[] getDevelopers() {
        return developers;
    }

    public String[] getGenres() {
        return genres;
    }

    public int getPrice() {
        return price;
    }

    public String getRating() {
        return rating;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlatforms(String[] platforms) {
        this.platforms = platforms;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDevelopers(String[] developers) {
        this.developers = developers;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void printGame() {
        System.out.println(this.toString());
        System.out.println("");
    }

    @Override
    public String toString() {
        String str = this.name + "\n" + this.releaseDate + "\n" +
                Arrays.toString(this.developers) + "\n" +
                Arrays.toString(this.genres) + "\n";
        String priceString = price + "";
        str += "$" + priceString.substring(0, priceString.length() - 2) + "." +
                priceString.substring(priceString.length() - 2);
        str += "\n" + this.rating;

        return str;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return Objects.equals(appID, game.appID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(appID);
    }
}

