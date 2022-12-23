package command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class AboutNode implements ICommand {
    private static final String infoAboutCommand = "Эта команда, рассказывает о боте и его создателях.";
    private static final String infoAboutBot = "Бот, показывающий текущую погоду. \n@Unknown_Persik, @Yoforik";

    public AboutNode(){}

    @Override
    public String getInfo() {
        return infoAboutCommand;
    }

    @Override
    public SendMessage doCommand(String text) {
        SendMessage msg = new SendMessage();
        msg.setText(infoAboutBot);
        return msg;
    }
}
