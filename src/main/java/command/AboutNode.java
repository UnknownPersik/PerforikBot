package command;

public class AboutNode implements ICommand {
    private static final String infoAboutCommand = "Эта команда, рассказывает о боте и его создателях.";
    private static final String infoAboutBot = "Бот, показывающий текущую погоду. \n@Unknown_Persik, @Yoforik";

    public AboutNode(){}

    @Override
    public String getInfo() {
        return infoAboutCommand;
    }

    @Override
    public String doCommand(String text) {
        return infoAboutBot;
    }
}
