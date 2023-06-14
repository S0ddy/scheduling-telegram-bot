import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.BotService;

public class TelegramBotDriver {
    public static void main(String[] args) {
        System.out.println("run");
        try {
        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        // Register our bot
            botsApi.registerBot(new BotService());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
