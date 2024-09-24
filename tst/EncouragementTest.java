import org.junit.Test;

public class EncouragementTest {

    @Test
    public void sendEncouragement() throws Exception {
        TelegramUtility.sendMessageToMyself("All work pays off!");
    }
}