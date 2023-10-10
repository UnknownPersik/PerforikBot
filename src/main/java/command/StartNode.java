package command;

import keyboard.KeyboardCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StartNode implements ICommand {
    private final KeyboardCreator keyboardCreator = new KeyboardCreator();
    private static final String infoAboutBot = "Привет, я бот показывающий погоду\n" +
            "Напиши /help, чтобы узнать о командах";
    private static final String infoAboutCommand = "Действительно ли надо писать help для этой команды?)";

    @Override
    public SendMessage doCommand(String string, Long id) {
        SendMessage msg = new SendMessage();
        msg.setText(infoAboutBot);
        keyboardCreator.setSimpleButtons(msg);
        return msg;
    }

    @Override
    public String getInfo() {
        return infoAboutCommand;
    }
}
