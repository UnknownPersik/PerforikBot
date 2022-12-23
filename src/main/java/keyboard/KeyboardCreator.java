package keyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyboardCreator {
    public void createButtonForHelpCommand(SendMessage message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();

        InlineKeyboardButton about = new InlineKeyboardButton();
        about.setText("about");
        about.setCallbackData("about");

        InlineKeyboardButton echo = new InlineKeyboardButton();
        about.setText("echo");
        about.setCallbackData("echo");

        InlineKeyboardButton weather = new InlineKeyboardButton();
        about.setText("weather");
        about.setCallbackData("weather");

        InlineKeyboardButton help = new InlineKeyboardButton();
        about.setText("help");
        about.setCallbackData("help");

        firstRow.add(about);
        firstRow.add(echo);
        secondRow.add(weather);
        secondRow.add(help);

        keyboard.add(firstRow);
        keyboard.add(secondRow);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }
}