package bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import command.HandlerNode;


public class TelegramBot extends TelegramLongPollingBot {
  private final String userName;
  private final String token;
  private final HandlerNode handler;
  public TelegramBot(String userName, String token) {
    this.userName = userName;
    this.token = token;
    handler = new HandlerNode();
  }

  private void sendMsg(String chatId, String text){
    SendMessage sendMessage = new SendMessage();
    sendMessage.enableMarkdown(true);
    sendMessage.setChatId(chatId);
    sendMessage.setText(text);
    try{
      execute(sendMessage);
    } catch (TelegramApiException e){
      e.printStackTrace();
    }
  }

  @Override
  @SneakyThrows
  public void onUpdateReceived(Update update) {
    Message temp = update.getMessage();
    String chatID = temp.getChatId().toString();
    String text = temp.getText();
    if (!temp.hasEntities()){
      sendMsg(chatID, text);
    }
    else{
      handleMessage(chatID, text);
    }
  }

  public void handleMessage(String chatID, String text){
    String msg = handler.checkCommand(text);
    if (msg != null) {
      try {
        execute(SendMessage.builder().text(msg).chatId(chatID).build());
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
    else{
      try {
        execute(SendMessage.builder().text("Такой команды нет попробуйте снова").chatId(chatID).build());
        } catch (TelegramApiException e){
        e.printStackTrace();
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
      } catch (InterruptedException interruptedException) {
        interruptedException.printStackTrace();
      }
    }
  }

  @Override
  public String getBotUsername() {
    return userName;
  }

  @Override
  public String getBotToken() {
    return token;
  }
}
