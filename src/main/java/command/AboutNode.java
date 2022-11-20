package command;

public class AboutNode implements ICommand {
    private final String infoAboutCommand;

    public AboutNode(){
        infoAboutCommand = "Эта команда, рассказывает о боте и его создателях.";
    }

    @Override
    public String getInfo() {
        return infoAboutCommand;
    }

    @Override
    public String doCommand(String text) {
        return "Бот, показывающий текущую погоду. \n@Unknown_Persik";
    }
}
