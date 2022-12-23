package parser;

import entity.WeatherEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class JsonParserTest {
	private final JsonParser jsonParser = new JsonParser();

	@Test
	void testCorrectMoscowSnow() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowSnow.json"));
		WeatherEntity result = jsonParser.parser(moscowInfo);
		WeatherEntity test = new WeatherEntity(
				"Snow",
				"3.71",
				"-5.08",
				"-10.28",
				"94",
				"Moscow");
		Assertions.assertTrue(test.equal(result));
	}

	@Test
	void testCorrectMoscowClouds() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowClouds.json"));
		WeatherEntity result = jsonParser.parser(moscowInfo);
		WeatherEntity test = new WeatherEntity(
				"Clouds",
				"1.43",
				"-2.13",
				"-7.24",
				"56",
				"Moscow");
		Assertions.assertTrue(test.equal(result));
	}

	@Test
	void testCorrectMoscowRain() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		WeatherEntity result = jsonParser.parser(moscowInfo);
		WeatherEntity test = new WeatherEntity(
				"Rain",
				"5.31",
				"5.23",
				"1.32",
				"98",
				"Moscow");
		Assertions.assertTrue(test.equal(result));
	}

	@Test
	void testIncorrectPrecipitation() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		WeatherEntity result = jsonParser.parser(moscowInfo);
		WeatherEntity test = new WeatherEntity(
				"Snow",
				"5.31",
				"5.23",
				"1.32",
				"98",
				"Moscow");
		Assertions.assertNotEquals(result.getWeather(), test.getWeather());
	}

	@Test
	void testIncorrectWindSpeed() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		WeatherEntity result = jsonParser.parser(moscowInfo);
		WeatherEntity test = new WeatherEntity(
				"Rain",
				"3.31",
				"5.23",
				"1.32",
				"98",
				"Moscow");
		Assertions.assertNotEquals(result.getWind(), test.getWind());
	}

	@Test
	void testIncorrectTemperature() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		WeatherEntity result = jsonParser.parser(moscowInfo);
		WeatherEntity test = new WeatherEntity(
				"Rain",
				"5.31",
				"3.23",
				"1.32",
				"98",
				"Moscow");
		Assertions.assertNotEquals(result.getTemp(), test.getTemp());
	}

	@Test
	void testIncorrectFeelTemperature() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		WeatherEntity result = jsonParser.parser(moscowInfo);
		WeatherEntity test = new WeatherEntity(
				"Rain",
				"5.31",
				"5.23",
				"-1.32",
				"98",
				"Moscow");
		Assertions.assertNotEquals(result.getFeelTemp(), test.getFeelTemp());
	}

	@Test
	void testIncorrectHumidity() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		WeatherEntity result = jsonParser.parser(moscowInfo);
		WeatherEntity test = new WeatherEntity(
				"Rain",
				"5.31",
				"5.23",
				"1.32",
				"78",
				"Moscow");
		Assertions.assertNotEquals(result.getHumidity(), test.getHumidity());
	}

	@Test
	void testIncorrectCity() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		WeatherEntity result = jsonParser.parser(moscowInfo);
		WeatherEntity test = new WeatherEntity(
				"Rain",
				"5.31",
				"5.23",
				"1.32",
				"98",
				"Ekaterinburg");
		Assertions.assertNotEquals(result.getCity(), test.getCity());
	}

	@Test
	void testIncorrectInfo() throws IOException {
		byte[] moscowInfo = Files.readAllBytes(Paths.get("moscowRain.json"));
		WeatherEntity result = jsonParser.parser(moscowInfo);
		WeatherEntity test = new WeatherEntity(
				"Snow",
				"10.3",
				"-25.23",
				"-31.32",
				"64",
				"Yakutsk");
		Assertions.assertFalse(test.equal(result));
	}
}