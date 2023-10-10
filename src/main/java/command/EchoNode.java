package command;

import keyboard.KeyboardCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class EchoNode implements ICommand {
    public static final String infoAboutCommand = "Выводит то, что вы введете после echo";
    private final KeyboardCreator keyboardCreator = new KeyboardCreator();

    public EchoNode() {
    }

    public String getInfo() {
        return infoAboutCommand;
    }

    public SendMessage doCommand(String text, Long id) {
        SendMessage msg = new SendMessage();
        if (text == null) {
            msg.setText("Введите что-нибудь, чтобы бот вывел то же самое)");
            keyboardCreator.setSimpleButtons(msg);
            return msg;
        }
        msg.setText(text);
        keyboardCreator.setSimpleButtons(msg);
        return msg;
    }
}
