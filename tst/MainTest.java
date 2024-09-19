import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

public class MainTest {

    @Test
    public void testGames() throws Exception {
        String[] appIDs = { "1604030", "594650", "1245620", "1384160", "239140"};
        Game[] games = new Game[appIDs.length];
        for (int i = 0; i < appIDs.length; i++) {
            Game g = new Game(appIDs[i]);
            games[i] = g;
        }

        for (int i = 0; i < games.length; i++) {
            games[i].printGame();
            FileUtils.writeStringToFile(new File("games.txt"),games[i].toString() + "\n","UTF-8",true);
        }
    }
}


