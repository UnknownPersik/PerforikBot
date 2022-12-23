package bot;

import database.UserBase;
import entity.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import common.WorkWithMessage;

import java.util.ArrayList;

public class TelegramBot extends TelegramLongPollingBot {
    private final String userName;
    private final String token;
    private final WorkWithMessage workWithMessage;
    private final Keyboard keyboard;

    public TelegramBot(String userName, String token) {
        this.userName = userName;
        this.token = token;
        workWithMessage = new WorkWithMessage();
        keyboard = new Keyboard();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message msgTelegram;
        String inputText;
        Long chatID;
//        Long telegramID = update.getMessage().getChat().getId();
//        registerUser(telegramID);

        if (update.hasCallbackQuery()) {
            msgTelegram = update.getCallbackQuery().getMessage();
            inputText = update.getCallbackQuery().getData();
        } else {
            msgTelegram = update.getMessage();
            inputText = update.getMessage().getText();
        }

        chatID = msgTelegram.getChatId();
        SendMessage answer = workWithMessage.handleMessage(inputText);
        sendMsg(chatID, answer);
    }

    private void registerUser(Long id) {
        User user = new UserBase().findByTelegramId(id);
        if (user == null) {
            User newUser = new User();
            newUser.setId(id);
            user = new UserBase().save(newUser);
        }
    }

    public synchronized void sendMsg(long chatId, SendMessage sendMessage) {
        sendMessage.setChatId(chatId);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
