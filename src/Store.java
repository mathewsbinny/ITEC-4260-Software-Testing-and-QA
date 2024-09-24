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
                Game g = new Game(id);
                inv.add(g);
            }
        } catch(Exception e) {
            throw new RuntimeException("Loading error...");
        }
    }

    public Inventory getInventory() {
        return inv;
    }

    public void setInventory(Inventory inv) {
        this.inv = inv;
    }
}

