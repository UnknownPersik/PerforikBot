package command;

import database.UserBase;
import entity.Cities;
import database.CitiesBase;

import entity.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public class FavouriteCityNode implements ICommand {
    public static final String infoAboutCommand = "Позволяет вам установить 3 города" +
            ", для которых вы будете искать погоду чаще! \n" +
            "Нужно вводить 3 города через пробел, на английском, либо русском языке (регистр не учитывается).";

    private final WeatherNode weatherNode = new WeatherNode();

    public SendMessage doCommand(String cities, Long user_id) {
        SendMessage msg = new SendMessage();
        if (cities == null) {
            msg.setText("Вызовите команду снова и после нее введите название города(ов)");
        } else {
            User userEntity = new User();
            Cities cityEntity = new Cities();
            String[] listOfCities = cities.split(" ", 4);
            for (var i = 0; i < 3; i++) {
                String temp = listOfCities[i].toLowerCase();
                boolean cityExistsInDB = false;
                List<Cities> columnsFromDBByUserId = new UserBase().findCities(user_id).getCities();
                int countOfColumns = columnsFromDBByUserId.size();
                for (var column : columnsFromDBByUserId) {
                    String cityFromDBByUserId = column.getCities();
                    if (isValid(cityFromDBByUserId, temp)) {
                        cityExistsInDB = true;
                        break;
                    }
                }
                if (countOfColumns < 3) {
                    if (!cityExistsInDB) {
                        if (weatherNode.correctCity(temp)) {
                            userEntity.setId(user_id);
                            cityEntity.setUser(userEntity);
                            cityEntity.setCities(temp);
                            new CitiesBase().save(cityEntity);
                            msg.setText("Город(а) успешно записан(ы) в базу данных, вы же не против что мы собираем ваши данные?)");
                        } else {
                            msg.setText("Вы неправильно ввели название города: " + temp
                                    + "\nЛибо такого города не существует");
                        }
                    } else {
                        msg.setText("Город(а) " + temp + " есть в базе данных");
                    }
                } else {
                    msg.setText("В базе данных уже есть 3 города, удаление пока не добавлено(");
                }
            }
        }
        return msg;
    }

    @Override
    public String getInfo() {
        return infoAboutCommand;
    }

    private boolean isValid(String city, String temp) {
        return city.equals(temp);
    }
}
