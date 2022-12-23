package parser;

import javax.validation.constraints.NotNull;

public class WeatherEntity {
	private final String weather;
	private final String wind;
	private final String temp;
	private final String feelTemp;
	private final String humidity;
	private final String city;

	public WeatherEntity(String weather, String wind, String temp, String feelTemp, String humidity, String city) {
		this.weather = weather;
		this.wind = wind;
		this.temp = temp;
		this.feelTemp = feelTemp;
		this.humidity = humidity;
		this.city = city;
	}

	@NotNull
	public boolean equal(WeatherEntity weatherEntity) {
		return (weather.equals(weatherEntity.weather)
				&& wind.equals(weatherEntity.wind)
				&& temp.equals(weatherEntity.temp)
				&& feelTemp.equals(weatherEntity.feelTemp)
				&& humidity.equals(weatherEntity.humidity)
				&& city.equals(weatherEntity.city));
	}

	public String getWeather() {
		return weather;
	}

	public String getWind() {
		return wind;
	}

	public String getTemp() {
		return temp;
	}

	public String getFeelTemp() {
		return feelTemp;
	}

	public String getHumidity() {
		return humidity;
	}

	public String getCity() {
		return city;
	}
}
