package command;

import entity.Cities;
import keyboard.KeyboardCreator;
import parser.JsonParser;
import entity.WeatherEntity;
import database.UserBase;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherNode implements ICommand {

    private final KeyboardCreator keyboardCreator = new KeyboardCreator();
    public static final String infoAboutCommand = """
            Это команда показывает текущую погоду в выбранном вами городе
            Тип осадков
            Температуру (°C)
            Скорость ветра (м/с)
            Влажность (%)
            Чтобы получить информацию о погоде выберите один из любимых городов
            Либо напишите /weather <Название города>""";

    public WeatherNode() {
    }

    @Override
    public SendMessage doCommand(String text, Long id) {
        SendMessage msg = new SendMessage();
        if (text != null) {
            JsonParser parser = new JsonParser();
            try {
                URL link = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + text.split(" ")[0]
                        + "&units=metric&appid=" + getToken());
                byte[] infoAboutWeather = link.openStream().readAllBytes();
                WeatherEntity weatherEntity = parser.parser(infoAboutWeather);
                msg.setText(makeStr(weatherEntity));
            } catch (IOException e) {
                e.printStackTrace();
                msg.setText("Вы не правильно ввели название города \uD83D\uDE02");
            }
        } else {
            List<Cities> columnsFromCitiesBase = new ArrayList<>(new UserBase().findCities(id).getCities());
            StringBuilder stringOfCities = new StringBuilder();
            for (var column : columnsFromCitiesBase) {
                stringOfCities.append(column.getCities()).append(" ");
            }
            var result = stringOfCities.toString();
            msg.setText("""
                    Выберите один из трех любимых городов
                    Либо напишите /weather <Название города>.
                    """);
            keyboardCreator.setSimpleButtonForWeather(msg, result);
        }
        return msg;
    }

    @Override
    public String getInfo() {
        return infoAboutCommand;
    }

    private static String getToken() {
        return System.getenv("WEATHER_TOKEN");
    }

    private String makeStr(WeatherEntity weather) {
        return "Осадки: " + weather.getWeather() + "\n" +
                "Скорость ветра: " + weather.getWind() + " м/с\n" +
                "Температура: " + weather.getTemp() + " °C\n" +
                "Ощущается: " + weather.getFeelTemp() + " °C\n" +
                "Влажность: " + weather.getHumidity() + " %\n" +
                "Город: " + weather.getCity();
    }

    public boolean correctCity(String city) {
        try {
            URL testLink = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city.split(" ")[0]
                    + "&units=metric&appid=" + getToken());
            testLink.openStream();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
