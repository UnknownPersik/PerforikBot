package command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class EchoNode implements ICommand {
    public static final String infoAboutCommand = "Выводит то, что вы введете после echo";

    public EchoNode(){}

    public String getInfo(){
        return infoAboutCommand;
    }

    public SendMessage doCommand(String text){
        SendMessage msg = new SendMessage();
        if (text == null){
            msg.setText("Введите что-нибудь, чтобы бот вывел то же самое)");
            return msg;
        }
        msg.setText(text);
        return msg;
    }
}
