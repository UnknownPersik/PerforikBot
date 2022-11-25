package command;

import java.util.HashMap;

public class HelpNode implements ICommand {
    public final String infoAboutCommands;
    public final String infoAboutCommand = "Показывает список команд, доступных в боте";
    private final HashMap<String, ICommand> commandHashMap;

    public HelpNode(HashMap<String, ICommand> map){
        commandHashMap = map;
        StringBuilder strTemp = new StringBuilder();
        strTemp.append("Бот имеет следующие команды \n");
        for (String key : commandHashMap.keySet()){
            strTemp.append("/").append(key).append("\n"); // А почему нельзя было написать strTemp.append("/" + key + "\n")
        }
        infoAboutCommands = strTemp.toString();

    }

  @Override
  public String doCommand(String text) {
    if (text != null) {
      if (commandHashMap.containsKey(text)) {
        return commandHashMap.get(text).getInfo();
      }
      return null;
    }
    return infoAboutCommands;
	}

    @Override
    public String getInfo() {
        return infoAboutCommand;
    }
}
