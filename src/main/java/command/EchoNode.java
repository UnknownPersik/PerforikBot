package command;

public class EchoNode implements ICommand {
    public static final String infoAboutCommand = "Выводит то, что вы введете после echo";

    public EchoNode(){}

    public String getInfo(){
        return infoAboutCommand;
    }

    public String doCommand(String text){
        if (text == null){
            return "Введите что-нибудь, чтобы бот вывел то же самое)";
        }
        return text;
    }
}
