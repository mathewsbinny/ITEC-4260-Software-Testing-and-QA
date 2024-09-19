/**
 * Author: Mathews Binny
 * Date: 08.27.2024
 * Class: Inventory.java
 * Purpose: Maintain a list of games that are available for sale.
 */
import java.util.ArrayList;

public class Inventory {

    private ArrayList<Game> games;

    public Inventory() {
        games = new ArrayList<>();
    }

    public int getSize() {
        return games.size();
    }

    public void add(Game game) {
        if (!games.contains(game)) {
            games.add(game);
        } else {
            int index = games.indexOf(game);
            games.set(index, game);
        }
    }

    public void remove(Game game) {
        games.remove(game);
    }

}


