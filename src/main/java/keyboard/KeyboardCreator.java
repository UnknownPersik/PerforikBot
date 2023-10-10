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

    public void setSimpleButtonForWeather(SendMessage message, String text) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        String[] cities = text.split(" ");

        message.setReplyMarkup(inlineKeyboardMarkup);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();

        InlineKeyboardButton firstCity = new InlineKeyboardButton();
        InlineKeyboardButton secondCity = new InlineKeyboardButton();
        InlineKeyboardButton thirdCity = new InlineKeyboardButton();

        firstCity.setText(cities[0]);
        secondCity.setText(cities[1]);
        thirdCity.setText(cities[2]);

        firstCity.setCallbackData("/weather " + cities[0]);
        secondCity.setCallbackData("/weather " + cities[1]);
        thirdCity.setCallbackData("/weather " + cities[2]);

        firstRow.add(firstCity);
        firstRow.add(secondCity);
        firstRow.add(thirdCity);

        keyboard.add(firstRow);

        inlineKeyboardMarkup.setKeyboard(keyboard);
    }

    public void setButtonForHelp(SendMessage message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        message.setReplyMarkup(inlineKeyboardMarkup);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        List<InlineKeyboardButton> thirdRow = new ArrayList<>();

        InlineKeyboardButton about = new InlineKeyboardButton();
        InlineKeyboardButton echo = new InlineKeyboardButton();
        InlineKeyboardButton weather = new InlineKeyboardButton();
        InlineKeyboardButton help = new InlineKeyboardButton();
        InlineKeyboardButton favCity = new InlineKeyboardButton();

        about.setText("about");
        about.setCallbackData("/help about");
        echo.setText("echo");
        echo.setCallbackData("/help echo");
        weather.setText("weather");
        weather.setCallbackData("/help weather");
        help.setText("help");
        help.setCallbackData("/help help");
        favCity.setText("favourite city");
        favCity.setCallbackData("/help favourite_city");

        firstRow.add(about);
        firstRow.add(echo);
        secondRow.add(weather);
        secondRow.add(help);
        thirdRow.add(favCity);

        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);

        inlineKeyboardMarkup.setKeyboard(keyboard);
    }
}