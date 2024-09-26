import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StoreTest {

    private final String TEST_STORE_URL = "https://gist.githubusercontent.com/tacksoo/349702bd06852814fba06a4df48e32d8/raw/5fb59f716e9069ac186b1994376c85823a65e335/myinventory.csv";
    private Store store;

    @Before
    public void setUp() {
        store = new Store();
        // Load inventory from a test CSV (ensure this is accessible)
        store.loadInventoryFromWeb(TEST_STORE_URL);
    }

    @Test
    public void testStoreInventory() {
        //empty store
        Store store = new Store();
        assertEquals(0, store.getInventory().getSize());
        //testing with test store
        Store testStore = new Store();
        testStore.loadInventoryFromWeb(TEST_STORE_URL);
        assertEquals(3, testStore.getInventory().getSize());
    }

    @Test
    public void testLoadInventory() {
        assertEquals(3, store.getInventory().getSize()); // Adjust based on CSV provided
    }

    @Test
    public void testFindCheapestGame() {
        Game cheapestGame = store.getInventory().findCheapestGame();
        assertNotNull(cheapestGame);
        assertEquals("Lethal Company", cheapestGame.getName());
        assertEquals(999, cheapestGame.getPrice());
    }

    @Test
    public void testFindMostExpensiveGame() {
        Game mostExpensiveGame = store.getInventory().findMostExpensiveGame();
        assertNotNull(mostExpensiveGame);
        assertEquals("Baldur's Gate 3", mostExpensiveGame.getName());
        assertEquals(5999, mostExpensiveGame.getPrice());
    }

    @Test
    public void testGetAveragePriceOfAllGames() {
        double averagePrice = store.getInventory().getAveragePriceOfAllGames();
        assertEquals(29.99, averagePrice, 0.01);
    }
}


