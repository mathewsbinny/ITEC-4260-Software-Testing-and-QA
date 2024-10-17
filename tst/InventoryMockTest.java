import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class InventoryMockTest {

    @Test
    public void testCheapestGame() {
        InventoryInterface inv = Mockito.mock(InventoryInterface.class);
        Game g = Mockito.mock(Game.class);
        Mockito.when(g.getName()).thenReturn("Madden NFL");
        Mockito.when(inv.findCheapestGame()).thenReturn(g);
        Assert.assertEquals("Madden NFL", inv.findCheapestGame().getName());
    }

    @Test
    public void testMostExpensiveGame() {
        InventoryInterface inv = Mockito.mock(InventoryInterface.class);
        Game g = Mockito.mock(Game.class);
        Mockito.when(g.getName()).thenReturn("Super Mario Bros");
        Mockito.when(inv.findMostExpensiveGame()).thenReturn(g);
        Assert.assertEquals("Super Mario Bros", inv.findMostExpensiveGame().getName());
    }

    @Test
    public void testAveragePriceOfAllGames() {
        InventoryInterface inv = Mockito.mock(InventoryInterface.class);
        Mockito.when(inv.getAveragePriceOfAllGames()).thenReturn(60.0);
        Assert.assertEquals(60.0, inv.getAveragePriceOfAllGames(), 0);
    }
}
