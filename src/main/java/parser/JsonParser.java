package parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.WeatherEntity;

import java.io.IOException;

public class JsonParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public WeatherEntity parser(byte[] info) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(info);
        JsonNode weatherData = jsonNode.path("weather");
        String weather = weatherData.elements().next().path("main").asText();
        String wind = jsonNode.path("wind").path("speed").asText();
        String temp = jsonNode.path("main").path("temp").asText();
        String feelTemp = jsonNode.path("main").path("feels_like").asText();
        String humidity = jsonNode.path("main").path("humidity").asText();
        String city = jsonNode.path("name").asText();
        return new WeatherEntity(weather, wind, temp, feelTemp, humidity, city);
    }
}
