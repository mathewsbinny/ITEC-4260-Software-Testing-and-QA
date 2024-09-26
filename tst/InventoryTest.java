import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {

    private Inventory inv;

    @Before
    public void setUp() {
        Game g = new Game("1", "Super Mario", new String[]{"NES"}, "1/1/1983", null, null, 5999, "E" );
        Game g2 = new Game("3", "Super Mario", new String[]{"NES"}, "1/1/1983", null, null, 5999, "E" );
        Game g3 = new Game("4", "Legend of Zelda", new String[]{"NES"}, "1/1/1984", null, null, 5999, "E" );
        Game g4 = new Game("5", "Minecraft", new String[]{"PC"}, "1/1/2011", null, null, 1999, "E10+");
        inv = new Inventory();
        inv.add(g);
        inv.add(g2);
        inv.add(g3);
        inv.add(g4);
    }

    @Test
    public void testInventory() {
        Inventory inv = new Inventory();
    }

    @Test
    public void testEquality() {
        Game g = new Game("1", "Super Mario", new String[]{"NES"}, "1/1/1983", null, null, 5999, null );
        Game g2 = new Game("3", "Super Mario", new String[]{"NES"}, "1/1/1983", null, null, 5999, null );
        Assert.assertNotEquals(g, g2);
    }

    @Test
    public void testAddSameGameToInventory() {
        Game g = new Game("1", "Super Mario", new String[]{"NES"}, "1/1/1983", null, null, 5999, null );
        inv.add(g);
        Assert.assertEquals("adding same game increased the inventory!",4, inv.getSize());
    }

    @Test
    public void testAddGame() {
        Game g = new Game("42", "Roblox", new String[]{"PC"}, "1/1/2010", null, null, 0, null );
        inv.add(g);
        Assert.assertEquals("game not added to inventory!",5, inv.getSize());
    }

    @Test
    public void testRemoveGame() {
        Game g = new Game("1", "Super Mario", new String[]{"NES"}, "1/1/1983", null, null, 5999, null );
        inv.remove(g);
        Assert.assertEquals(3, inv.getSize());
        inv.remove(g);
        Assert.assertEquals(3, inv.getSize());
    }

    @Test
    public void testFindCheapestGame() {
        Game cheapestGame = inv.findCheapestGame();
        Assert.assertEquals("Minecraft", cheapestGame.getName());
    }

    @Test
    public void testFindMostHighlyRatedGame() {
        Game highestRatedGame = inv.findMostHighlyRatedGame();
        Assert.assertEquals("Minecraft", highestRatedGame.getName()); // Assuming "E10+" is considered higher
    }

    @Test
    public void testPrintAveragePriceOfAllGames() {
        inv.printAveragePriceOfAllGames();
    }

    @Test
    public void testEmptyInventoryForCheapest() {
        Inventory emptyInv = new Inventory();
        Assert.assertNull(emptyInv.findCheapestGame());
    }

    @Test
    public void testEmptyInventoryForHighestRated() {
        Inventory emptyInv = new Inventory();
        Assert.assertNull(emptyInv.findMostHighlyRatedGame());
    }
}