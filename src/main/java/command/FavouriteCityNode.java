package command;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class FavouriteCityNode implements ICommand {
    public static final String infoAboutCommand = "Позволяет вам установить 3 города" +
            ", для которых вы будете искать погоду чаще! \n" +
            "Нужно вводить 3 города через пробел, на английском, либо русском языке (регистр не учитывается).";

    private final WeatherNode weatherNode = new WeatherNode();

    @SneakyThrows
    @Override
    public SendMessage doCommand(String cities) {
        SendMessage msg = new SendMessage();
        String[] listOfCities = cities.split(" ");
        if (listOfCities.length < 3) {
            msg.setText("Вы ввели недостаточно городов. \n" +
                    "Напишите снова название команды и после 3 города, не ошибитесь \uD83D\uDE0E");
        }
        for (var i = 0; i < 3; i++) {
            if (listOfCities[i] != null) {
                if (weatherNode.correctCity(listOfCities[i])) {
                    continue;
                }
                msg.setText("Вы неправильно ввели название города: " + listOfCities[i]
                        + "\nЛибо такого города не существует");
            }
        }
        msg.setText("Города успешно записаны в базу данных, вы же не против что мы собираем ваши данные?)");
        return msg;
    }

    @Override
    public String getInfo() {
        return infoAboutCommand;
    }
}
