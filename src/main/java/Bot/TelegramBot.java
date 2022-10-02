package Bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBot  extends TelegramLongPollingBot {

    private final String userName;
    private final String token;

    public TelegramBot(String userName, String token){
        this.userName = userName;
        this.token = token;
    }
    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update){
        if (update.hasMessage() && update.getMessage().hasText()){
            Message message = update.getMessage();
            execute(SendMessage.builder().chatId(message.getChatId().toString()).text("Hello " + message.getFrom().getUserName().toString()).build());
        }
    }

    public void botConnect() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            try {
                Thread.sleep(10000);
                botConnect();
            } catch (InterruptedException interruptedException){
                interruptedException.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername(){
        return userName;
    }

    @Override
    public String getBotToken(){
        return token;
    }

}
