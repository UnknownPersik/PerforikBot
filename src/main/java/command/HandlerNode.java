package command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;

public class HandlerNode {
    private final HashMap<String, ICommand> commands;

    public HandlerNode(){
         commands = new HashMap<>();
         commands.put("about", new AboutNode());
         commands.put("weather", new WeatherNode());
         commands.put("echo", new EchoNode());
         commands.put("start", new StartNode());
         commands.put("favourite_city", new FavouriteCityNode());
         commands.put("help", new HelpNode(commands));
    }

    public SendMessage checkCommand(String query){
        String[] line = query.substring(1).split(" ", 2);
        String command = line[0];
        String parameter = null;

        if (line.length > 1) {
            parameter = line[1];
        }
        if (commands.containsKey(command)){
            return commands.get(command).doCommand(parameter);
        }
        return null;
    }
}
