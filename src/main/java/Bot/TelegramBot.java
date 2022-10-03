package Bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
        String chatID = update.getMessage().getChatId().toString();
        String message = update.getMessage().getText();
        if (!update.getMessage().hasEntities()) {
            sendMsg(chatID, message);
        }
        else {
            if (message.regionMatches(0,"/echo", 0, 5)){
                execute(SendMessage.builder()
                        .text("You sent: \n\n" + message.substring(6))
                        .chatId(chatID)
                        .build());
            }
            else {
                handleMessage(update.getMessage());
            }
        }
    }

    public synchronized void sendMsg(String chatId, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
    @SneakyThrows
    public void handleMessage(Message message) {
        Costyl costyl = new Costyl();
        if(message.hasText() && message.hasEntities()) {
            String msg = costyl.beFixed(message.getText());
            if (msg != null) {
                execute(SendMessage.builder()
                        .text(msg)
                        .chatId(message.getChatId().toString())
                        .build());
            }
            else {
                execute(SendMessage.builder()
                        .text("For have more information type /help")
                        .chatId(message.getChatId().toString())
                        .build());
            }
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
