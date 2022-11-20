package command;

import java.util.HashMap;

public class HandlerNode {
    private final HashMap<String, ICommand> commands;

    public HandlerNode(){
         commands = new HashMap<>();
         commands.put("about", new AboutNode());
         commands.put("help", new HelpNode(commands));
         commands.put("weather", new WeatherNode());
         commands.put("echo", new EchoNode());
    }

    public String checkCommand(String query){
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
