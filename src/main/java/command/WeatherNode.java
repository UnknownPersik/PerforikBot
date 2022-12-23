package command;

import parser.JsonParser;
import entity.WeatherEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherNode implements ICommand {

    public static final String infoAboutCommand = """
            Это команда показывает текущую погоду в выбранном вами городе
            Тип осадков
            Температуру (°C)
            Скорость ветра (м/с)
            Влажность (%)
            Чтобы получить информацию о погоде введите
            /weather <Название города>""";

    public WeatherNode() {}

    @Override
    public String doCommand(String text) {
        if (text != null) {
            JsonParser parser = new JsonParser();
            try {
                URL link = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + text.split(" ")[0]
                        + "&units=metric&appid=" + getToken());
                byte[] infoAboutWeather = link.openStream().readAllBytes();
                WeatherEntity weatherEntity = parser.parser(infoAboutWeather);
                return makeStr(weatherEntity);
            } catch (IOException e) {
                e.printStackTrace();
                return "Вы не правильно ввели название города \uD83D\uDE02";
            }
        }
        return "Введите название города после команды, чтобы узнать текущую погоду.";
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

    public boolean correctCity(String city) throws MalformedURLException {
        URL testLink = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city.split(" ")[0]
                + "&units=metric&appid=" + getToken());
        try {
            testLink.openStream();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
