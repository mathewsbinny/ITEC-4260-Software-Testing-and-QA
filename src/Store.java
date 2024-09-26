import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Store {
    private Inventory inv;

    public Store () {
        inv = new Inventory();
    }

    public void loadInventoryFromWeb(String url) {
        //parse CSV file and load inventory
        //parser --> record

        try {
            URL urlObj = new URL(url);
            CSVParser parser = CSVParser.parse(urlObj, StandardCharsets.UTF_8,
                    CSVFormat.DEFAULT.builder().setHeader().build());
            for(CSVRecord record : parser) {
                String id = record.get("appID");
                String platform = record.get("platform");
                String name = record.get("name");
                String releaseDate = record.get("releaseDate");
                String developer = record.get("developer");
                String genre = record.get("genre");
                int retailPrice = Integer.parseInt(record.get("retailPrice"));
                System.out.println("Loading game: " + name + " with price: " + retailPrice);
                int recommendationCount = Integer.parseInt(record.get("recommendationCount"));

                // Convert platform, developer, genre to arrays if needed
                //String[] platforms = new String[]{platform}; // Wrap in array
                //String[] developers = new String[]{developer}; // Wrap in array
                //String[] genres = new String[]{genre}; // Wrap in array

                Game g = new Game(id);
                inv.add(g);
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Loading error...",e);
        }
    }

    public Game findCheapestGame() {
        return inv.findCheapestGame();
    }

    public Game findMostExpensiveGame() {
        return inv.findMostExpensiveGame();
    }

    public double getAveragePriceOfAllGames() {
        return inv.getAveragePriceOfAllGames();
    }

    public Inventory getInventory() {
        return inv;
    }

    public void setInventory(Inventory inv) {
        this.inv = inv;
    }
}

