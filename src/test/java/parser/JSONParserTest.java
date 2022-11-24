package parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class JSONParserTest {
	private final JSONParser jsonParser = new JSONParser();

	@Test
	void testCorrectMoscowSnow() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowSnow.json"));
		String[] result = jsonParser.parser(moscowInfo);
		String[] test = {"Snow", "3.71", "-5.08", "-10.28", "94", "Moscow"};
		Assertions.assertArrayEquals(result, test);
	}

	@Test
	void testCorrectMoscowClouds() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowClouds.json"));
		String[] result = jsonParser.parser(moscowInfo);
		String[] test = {"Clouds", "1.43", "-2.13", "-7.24", "56", "Moscow"};
		Assertions.assertArrayEquals(result, test);
	}

	@Test
	void testCorrectMoscowRain() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		String[] result = jsonParser.parser(moscowInfo);
		String[] test = {"Rain", "5.31", "5.23", "1.32", "98", "Moscow"};
		Assertions.assertArrayEquals(result, test);
	}

	@Test
	void testIncorrectPrecipitation() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		String[] result = jsonParser.parser(moscowInfo);
		String[] test = {"Snow", "5.31", "5.23", "1.32", "98", "Moscow"};
		Assertions.assertNotEquals(result[0], test[0]);
	}

	@Test
	void testIncorrectWindSpeed() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		String[] result = jsonParser.parser(moscowInfo);
		String[] test = {"Rain", "3.31", "5.23", "1.32", "98", "Moscow"};
		Assertions.assertNotEquals(result[1], test[1]);
	}

	@Test
	void testIncorrectTemperature() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		String[] result = jsonParser.parser(moscowInfo);
		String[] test = {"Rain", "5.31", "3.23", "1.32", "98", "Moscow"};
		Assertions.assertNotEquals(result[2], test[2]);
	}

	@Test
	void testIncorrectFeelTemperature() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		String[] result = jsonParser.parser(moscowInfo);
		String[] test = {"Rain", "5.31", "5.23", "-1.32", "98", "Moscow"};
		Assertions.assertNotEquals(result[3], test[3]);
	}

	@Test
	void testIncorrectHumidity() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		String[] result = jsonParser.parser(moscowInfo);
		String[] test = {"Rain", "5.31", "5.23", "1.32", "78", "Moscow"};
		Assertions.assertNotEquals(result[4], test[4]);
	}

	@Test
	void testIncorrectCity() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		String[] result = jsonParser.parser(moscowInfo);
		String[] test = {"Rain", "5.31", "5.23", "1.32", "98", "Ekaterinburg"};
		Assertions.assertNotEquals(result[5], test[5]);
	}

	@Test
	void testIncorrectInfo() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		String[] result = jsonParser.parser(moscowInfo);
		String[] test = {"Snow", "10.3", "-25.23", "-31.32", "64", "Yakutsk"};
		Assertions.assertNotEquals(result[5], test[5]);
	}
}