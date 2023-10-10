package command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HandlerNodeTest {
    private final HandlerNode handlerNode = new HandlerNode();
    private final Long user_id = 123L;

    @Test
    void checkCommandAbout() {
        String query = "/about";
        String result = handlerNode.checkCommand(query, user_id).getText();
        String equalResult = "Бот, показывающий текущую погоду. \n@Unknown_Persik, @Yoforik";
        Assertions.assertEquals(result, equalResult);
    }

    @Test
    void checkCommandEchoWithEmptyMessage() {
        String query = "/echo";
        String result = handlerNode.checkCommand(query, user_id).getText();
        String equalResult = "Введите что-нибудь, чтобы бот вывел то же самое)";
        Assertions.assertEquals(result, equalResult);
    }

    @Test
    void checkCommandEchoWithNotEmptyMessage() {
        String query = "/echo Привет я бот";
        String result = handlerNode.checkCommand(query, user_id).getText();
        Assertions.assertEquals(result, query.split(" ", 2)[1]);
    }

    @Test
    void checkCommandWeatherWithWrongCity() {
        String query = "/weather Екб";
        String result = handlerNode.checkCommand(query, user_id).getText();
        String equalResult = "Вы не правильно ввели название города \uD83D\uDE02";
        Assertions.assertEquals(result, equalResult);
    }

    @Test
    void checkCommandWeatherWithCorrectCity() {
        String query = "/weather Екатеринбург";
        String result = handlerNode.checkCommand(query, user_id).getText();
        String equalResult = handlerNode.checkCommand(query, user_id).getText();
        Assertions.assertEquals(result, equalResult);
    }

    @Test
    void checkCommandHelpWithEmptyMessage() {
        String query = "/help";
        String result = handlerNode.checkCommand(query, user_id).getText();
        String equalResult = """
                Бот имеет следующие команды\s
                /about
                /weather
                /start
                /echo
                /favourite_city
                Для познания работы команды нажмите на соответствующую кнопку""";
        Assertions.assertEquals(result, equalResult);
    }

    @Test
    void checkCommandHelpAbout() {
        String query = "/help about";
        String result = handlerNode.checkCommand(query, user_id).getText();
        String equalResult = "Эта команда, рассказывает о боте и его создателях.";
        Assertions.assertEquals(result, equalResult);
    }

    @Test
    void checkCommandHelpEcho() {
        String query = "/help echo";
        String result = handlerNode.checkCommand(query, user_id).getText();
        String equalResult = "Выводит то, что вы введете после echo";
        Assertions.assertEquals(result, equalResult);
    }

    @Test
    void checkCommandHelpWeather() {
        String query = "/help weather";
        String result = handlerNode.checkCommand(query, user_id).getText();
        String equalResult = """
                Это команда показывает текущую погоду в выбранном вами городе
                Тип осадков
                Температуру (°C)
                Скорость ветра (м/с)
                Влажность (%)
                Чтобы получить информацию о погоде выберите один из любимых городов
                Либо напишите /weather <Название города>""";
        Assertions.assertEquals(result, equalResult);
    }

    @Test
    void checkCommandHelpStart() {
        String query = "/help start";
        String result = handlerNode.checkCommand(query, user_id).getText();
        String equalResult = "Действительно ли надо писать help для этой команды?)";
        Assertions.assertEquals(result, equalResult);
    }

    @Test
    void checkCommandHelpHelp() {
        String query = "/help help";
        String result = handlerNode.checkCommand(query, user_id).getText();
        String equalResult = "Показывает список команд, доступных в боте";
        Assertions.assertEquals(result, equalResult);
    }

    @Test
    void checkCommandHelpFavouriteCity() {
        String query = "/help favourite_city";
        String result = handlerNode.checkCommand(query, user_id).getText();
        String equalResult = "Позволяет вам установить 3 города" +
                ", для которых вы будете искать погоду чаще! \n" +
                "Нужно вводить 3 города через пробел, на английском, либо русском языке (регистр не учитывается).";
        Assertions.assertEquals(result, equalResult);
    }

    @Test
    void checkCommandHelpWrongCommand() {
        String query = "/help finish";
        String result = handlerNode.checkCommand(query, user_id).getText();
        String equalResult = null;
        Assertions.assertNotEquals(result, equalResult);
    }
}