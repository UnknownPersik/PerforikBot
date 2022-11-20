package command;

import java.util.HashMap;

public class HelpNode implements ICommand {
    public static final String infoAboutCommand =
            """
                    Бот имеет следующие команды:\s
                     /help - узнать о всех командах\s
                     /echo <something> - вывести <something> после команды
                     /about - рассказывает о боте
                     /weather <name_city> - выводит погоду в городе""";;
    private final HashMap<String, ICommand> commandHashMap;

    public HelpNode(HashMap<String, ICommand> map){
        commandHashMap = map;
    }

  @Override
  public String doCommand(String text) {
    if (text != null) {
      if (commandHashMap.containsKey(text)) {
        return commandHashMap.get(text).getInfo();
      }
      return null;
    }
    return infoAboutCommand;
	}


    @Override
    public String getInfo() {
        return infoAboutCommand;
    }
}
