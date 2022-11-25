package command;

import parser.JSONParser;

import java.io.IOException;
import java.net.URL;

public class WeatherNode implements ICommand {
    private static final String[] pattern = {"Осадки:\n", "Скорость ветра:м/c\n",
            "Температура:°C\n", "Ощущается:°C\n",
            "Влажность:%\n", "Город:\n"};
    public static final String infoAboutCommand = """
            Это команда показывает текущую погоду в выбранном вами городе
            Тип осадков
            Температуру (°C)
            Скорость ветра (м/с)
            Влажность (%)
            Чтобы получить информацию о погоде введите
            /weather <Название города>""";

    public WeatherNode(){}

    @Override
    public String doCommand(String text) {
        if (text != null){
            JSONParser parser = new JSONParser();
            try{
                URL link = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + text.split(" ")[0]
                        + "&units=metric&appid=" + getToken());
                byte[] infoAboutWeather = link.openStream().readAllBytes();
                return makeStr(parser.parser(infoAboutWeather));
            } catch (IOException e){
                e.printStackTrace();
                return "Вы не правильно ввели название города :)";
            }
        }
        return "Введите название города после команды, чтобы узнать текущую погоду.";
    }

    @Override
    public String getInfo() {
        return infoAboutCommand;
    }

    private static String getToken(){
        return System.getenv("WEATHER_TOKEN");
    }

    private static String makeStr(String[] result){
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < result.length; i++){
            String firstHalf = pattern[i].split(":")[0];
            String secondHalf = pattern[i].split(":")[1];
            result[i] = firstHalf + ": " + result[i] + " " + secondHalf;
            resultString.append(result[i]);
        }
        return resultString.toString();
    }
}