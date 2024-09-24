import org.apache.commons.io.IOUtils;
import java.net.URL;
import java.net.URLEncoder;

public class TelegramUtility {
    private static final String TOKEN = "7732368391:AAFDxIMhRNRpFvxPVWtzbpp-vByKlJjJe_Q";
    private static final String CHAT_ID = "7407939007";

    public static void sendMessageToMyself(String str) throws Exception {
        str = URLEncoder.encode(str, "UTF-8");
        String url = "https://api.telegram.org/bot" + TOKEN + "/sendMessage" + "?chat_id" + CHAT_ID
                + "&text=" + str;
        URL urlObj = new URL(url);
        String status = IOUtils.toString(urlObj.openStream(), "UTF-8");
        System.out.println(status);
    }

    public static void getChatID() throws Exception {
        String url = "https://api.telegram.org/bot" + TOKEN + "/getUpdates";
        URL urlObj = new URL(url);
        String str = IOUtils.toString(urlObj.openStream(), "UTF-8");
        System.out.println(str);
    }
}
