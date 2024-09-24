import org.junit.Test;

public class TelegramUtilityTest {

    @Test
    public void testTelegramUtility() throws Exception {
        TelegramUtility.getChatID();
    }

    @Test
    public void testSendMessage() throws Exception {
        TelegramUtility.sendMessageToMyself("Oh No! All systems are down!");
    }

    @Test
    public void testSendWhenTrouble() throws Exception {
        try {
            double x = 5/0;
        } catch (Exception e) {
            TelegramUtility.sendMessageToMyself("Oh no! arithmetic error!");
        }
    }
}


