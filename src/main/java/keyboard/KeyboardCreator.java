package keyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardCreator {
    public void setSimpleButtons(SendMessage message) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        message.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();

        firstRow.add(new KeyboardButton("/help"));
        firstRow.add(new KeyboardButton("/weather"));

        keyboard.add(firstRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public void setButtonForHelp(SendMessage message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        message.setReplyMarkup(inlineKeyboardMarkup);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();

        InlineKeyboardButton about = new InlineKeyboardButton();
        InlineKeyboardButton echo = new InlineKeyboardButton();
        InlineKeyboardButton weather = new InlineKeyboardButton();
        InlineKeyboardButton help = new InlineKeyboardButton();

        about.setText("about");
        about.setCallbackData("/help about");
        echo.setText("echo");
        echo.setCallbackData("/help echo");
        weather.setText("weather");
        weather.setCallbackData("/help weather");
        help.setText("help");
        help.setCallbackData("/help help");

        firstRow.add(about);
        firstRow.add(echo);
        secondRow.add(weather);
        secondRow.add(help);

        keyboard.add(firstRow);
        keyboard.add(secondRow);

        inlineKeyboardMarkup.setKeyboard(keyboard);
    }
}