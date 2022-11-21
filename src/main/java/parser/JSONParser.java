package parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class JSONParser{
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String[] parser(URL link1) throws IOException{
        byte[]  result = link1.openStream().readAllBytes();
        JsonNode jsonNode = objectMapper.readTree(result);
        JsonNode weatherData = jsonNode.path("weather");
        String weather = weatherData.elements().next().path("main").asText();
        String wind = jsonNode.path("wind").path("speed").asText();
        String temp = jsonNode.path("main").path("temp").asText();
        String feelTemp = jsonNode.path("main").path("feels_like").asText();
        String humidity = jsonNode.path("main").path("humidity").asText();
        String city = jsonNode.path("name").asText();
        return new String[]{weather, wind, temp, feelTemp, humidity, city};
    }
}
