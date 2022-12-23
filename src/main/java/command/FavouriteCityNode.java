package command;

import lombok.SneakyThrows;

public class FavouriteCityNode implements ICommand {
    public static final String infoAboutCommand = "Позволяет вам установить 3 города" +
            ", для которых вы будете искать погоду чаще! \n" +
            "Нужно вводить 3 города через пробел, на английском, либо русском языке (регистр не учитывается).";

    private final WeatherNode weatherNode = new WeatherNode();

    @SneakyThrows
    @Override
    public String doCommand(String cities) {
        String[] listOfCities = cities.split(" ");
        if (listOfCities.length < 3) {
            return "Вы ввели недостаточно городов. \n" +
                    "Напишите снова название команды и после 3 города, не ошибитесь \uD83D\uDE0E";
        }
        for (var i = 0; i < 3; i++) {
            System.out.println(listOfCities[i]);
            if (listOfCities[i] != null) {
                if (weatherNode.correctCity(listOfCities[i])) {
                    continue;
                }
                return "Вы неправильно ввели название города: " + listOfCities[i]
                        + "\nЛибо такого города не существует";
            }
        }
        return "Города успешно записаны в базу данных, вы же не против что мы собираем ваши данные?)";
    }

    @Override
    public String getInfo() {
        return infoAboutCommand;
    }
}
