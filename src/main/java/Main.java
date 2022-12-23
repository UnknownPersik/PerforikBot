import bot.TelegramBot;

//import database.HibernateUtil;
//import org.hibernate.Session;


public class Main {
    public static void main(String[] args) {
            TelegramBot bot = new TelegramBot(System.getenv("BOT_USERNAME"),
                    System.getenv("BOT_TOKEN"));
            bot.botConnect();

    }
}
