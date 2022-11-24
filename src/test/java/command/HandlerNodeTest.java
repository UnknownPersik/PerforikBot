package command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HandlerNodeTest {
    private final HandlerNode handlerNode = new HandlerNode();

	@Test
	void checkCommandAbout() {
        String query = "/about";
        String result = handlerNode.checkCommand(query);
        String equalResult = "Бот, показывающий текущую погоду. \n@Unknown_Persik, @Yoforik";
		Assertions.assertEquals(result, equalResult);
	}

	@Test
	void checkCommandEchoWithEmptyMessage() {
		String query = "/echo";
		String result = handlerNode.checkCommand(query);
    	String equalResult = "Введите что-нибудь после /echo для вывода на экран";
		Assertions.assertEquals(result, equalResult);
	}

	@Test
	void checkCommandEchoWithNotEmptyMessage() {
		String query = "/echo Привет я бот";
		String result = handlerNode.checkCommand(query);
		Assertions.assertEquals(result, query.split(" ", 2)[1]);
	}

	@Test
	void checkCommandWeatherWithWrongCity() {
		String query = "/weather Екб";
		String result = handlerNode.checkCommand(query);
		String equalResult = "Вы не правильно ввели название города :)";
		Assertions.assertEquals(result, equalResult);
	}

	@Test
	void checkCommandWeatherWithCorrectCity() {
		String query = "/weather Екатеринбург";
		String result = handlerNode.checkCommand(query);
		String equalResult = handlerNode.checkCommand(query);
		Assertions.assertEquals(result, equalResult);
	}

	@Test
	void checkCommandHelpWithEmptyMessage() {
		String query = "/help";
		String result = handlerNode.checkCommand(query);
		String equalResult = """
				Бот имеет следующие команды\s
				/about
				/weather
				/echo
				""";
		Assertions.assertEquals(result, equalResult);
	}

	@Test
	void checkCommandHelpAbout() {
		String query = "/help about";
		String result = handlerNode.checkCommand(query);
		String equalResult = "Эта команда, рассказывает о боте и его создателях.";
		Assertions.assertEquals(result, equalResult);
	}

	@Test
	void checkCommandHelpEcho() {
		String query = "/help echo";
		String result = handlerNode.checkCommand(query);
		String equalResult = "Выводит то, что вы введете после echo";
		Assertions.assertEquals(result, equalResult);
	}

	@Test
	void checkCommandHelpWeather() {
		String query = "/help weather";
		String result = handlerNode.checkCommand(query);
		String equalResult = """
            Это команда показывает текущую погоду в выбранном вами городе\s
            Тип осадков\s
            Температуру (°C)\s
            Скорость ветра (м/с)\s
            Влажность (%)""";
		Assertions.assertEquals(result, equalResult);
	}

	@Test
	void checkCommandHelpStart() {
		String query = "/help start";
		String result = handlerNode.checkCommand(query);
		String equalResult = "Действительно ли надо писать help для этой команды?)";
		Assertions.assertEquals(result, equalResult);
	}

	@Test
	void checkCommandHelpHelp() {
		String query = "/help help";
		String result = handlerNode.checkCommand(query);
		String equalResult = "Показывает список команд, доступных в боте";
		Assertions.assertEquals(result, equalResult);
	}

	@Test
	void checkWrongCommand() {
		String query = "/finish";
		String result = handlerNode.checkCommand(query);
		String equalResult = "Такой команды нет попробуйте снова";
		Assertions.assertNotEquals(result, equalResult);
	}

	@Test
	void checkCommandHelpWrongCommand() {
		String query = "/help finish";
		String result = handlerNode.checkCommand(query);
		String equalResult = "Такой команды нет попробуйте снова";
		Assertions.assertNotEquals(result, equalResult);
	}
}