package command;

import parser.JSONParser;

import java.io.IOException;
import java.net.URL;

public class WeatherNode implements ICommand {
    public static final String infoAboutCommand = "Это команда показывает погоду";

    public WeatherNode(){};

    @Override
    public String doCommand(String text) {
        JSONParser parser = new JSONParser();
        try{
            URL link = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + text
                    + "&units=metric&appid=" + getToken());
            return changeStr(parser.parseThisShit(link));
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getInfo() {
        return infoAboutCommand;
    }

    private static String getToken(){
        return System.getenv("WEATHER_TOKEN");
    }

    private static String changeStr(String[] result){
        return "Осадки: " + result[0] + "\n" +
                "Скорость ветра: " + result[1] + "\n" +
                "Температура: " + result[2] + "\n" +
                "Ощущается: " + result[3] + "\n" +
                "Влажность: " + result[4] + "\n" +
                "Город: " + result[5];

    }
}