package command;

public class EchoNode implements ICommand {
    public final String infoAboutCommand;

    public EchoNode(){
        infoAboutCommand = "Выводит то, что вы введете после echo";
    }

    public String getInfo(){
        return infoAboutCommand;
    }

    public String doCommand(String text){
        return text;
    }
}
