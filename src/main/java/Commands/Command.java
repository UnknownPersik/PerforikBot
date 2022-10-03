package Commands;

import java.util.Hashtable;

public abstract class Command {
    private final Hashtable<String, String> commands;
    public Command() {
        commands = new Hashtable<>();
        commands.put("/help", "Potom dopishem");
        commands.put("/about", "This Telegram Bot was written UnknownPersik and Yoforik");
        commands.put("/help echo", "Type /echo <text> for ...");
        commands.put("/help about", "Type /about for have information about creators of this bot");
    }
    public String checkCommand(String query){
        return query.startsWith("/")
                ? commands.get(query.split(" ", 1)[0])
                : "For more information type /help";
    }
}
