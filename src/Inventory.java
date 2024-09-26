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

    //TODO: Game findCheapestGame
    public Game findCheapestGame() {
        if (games.isEmpty()) {
            return null; // returns null if the inventory is empty
        }
        Game cheapestGame = games.get(0); //assume first game is cheapest before iterating through inventory
        for (Game game : games) {
            System.out.println("Comparing " + game.getName() + " with price: " + game.getPrice());
            if (game.getPrice() < cheapestGame.getPrice()) {
                cheapestGame = game;
                System.out.println("Cheapest game updated to: " + cheapestGame.getName() + " with price: " + cheapestGame.getPrice());
            }
        }
        System.out.println("Cheapest game found: " + cheapestGame.getName() + " with price: " + cheapestGame.getPrice());
        return cheapestGame;
    }

    //TODO: Game findMostExpensiveGame
    public Game findMostExpensiveGame() {
        if (games.isEmpty()) {
            return null; // returns null if the inventory is empty
        }
        Game mostExpensiveGame = games.get(0); //assume first game is most expensive before iterating through inventory
        for (Game game : games) {
            if (game.getPrice() > mostExpensiveGame.getPrice()) {
                mostExpensiveGame = game;
            }
        }
        return mostExpensiveGame;
    }


    //TODO: Game findMostHighlyRatedGame()
    public Game findMostHighlyRatedGame() {
        if (games.isEmpty()) {
            return null; // returns null if the inventory is empty
        }
        Game mostHighlyRatedGame = games.get(0); //assume first game is most highly rated before iterating through inventory
        for (Game game : games) {
            if (getRatingValue(game.getRating()) > getRatingValue(mostHighlyRatedGame.getRating())) {
                mostHighlyRatedGame = game;
            }
        }
        return mostHighlyRatedGame;
    }

    // Helper method to assign numerical values based on ESRB rating hierarchy
    private int getRatingValue(String rating) {
        switch (rating.toUpperCase()) {
            case "E":
                return 1;
            case "E10+":
                return 2;
            case "T":
                return 3;
            case "M":
                return 4;
            case "AO":
                return 5;
            default:
                return 0; // Default for unknown rating and rating pending
        }
    }

    //TODO: public void printAveragePriceOfAllGames()
    public void printAveragePriceOfAllGames() {
        if (games.isEmpty()) {
            System.out.println("No games available in inventory."); // returns statement if the inventory is empty
            return;
        }
        int totalPrice = 0;
        for (Game game : games) {
            totalPrice += game.getPrice();
        }
        double averagePrice = (double) totalPrice / games.size();
        System.out.println("The average price of all games is: $" + String.format("%.2f", averagePrice / 100)); // Price in cents
    }

    //TODO: public double getAveragePriceOfAllGames()
    public double getAveragePriceOfAllGames() {
        if (games.isEmpty()) {
            // returns null if the inventory is empty
            return 0.0;
        }
        int totalPrice = 0;
        for (Game game : games) {
            totalPrice += game.getPrice();
        }
        double averagePrice = (double) totalPrice / games.size();
        return averagePrice / 100; // Convert from cents to dollars
    }

}