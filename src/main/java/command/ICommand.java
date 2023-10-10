package command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface ICommand {
    SendMessage doCommand(String string, Long id);

    String getInfo();
}