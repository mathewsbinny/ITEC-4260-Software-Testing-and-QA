import org.junit.Assert;
import org.mockito.Mockito;

public class MockitoExampleTest {
    public void testMockObject() {
        Game game = Mockito.mock(Game.class);
        Mockito.when(game.getName()).thenReturn("Undertale");
        Assert.assertEquals("Undertale", game.getName());
    }
}
