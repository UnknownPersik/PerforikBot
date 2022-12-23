package command;

public class StartNode implements ICommand {
	private static final String infoAboutBot = "Привет, я бот показывающий погоду\n" +
			"Напиши /help, чтобы узнать о командах";
	private static final String infoAboutCommand = "Действительно ли надо писать help для этой команды?)";
	@Override
	public String doCommand(String string) {
		return infoAboutBot;
	}

	@Override
	public String getInfo() {
		return infoAboutCommand;
	}
}
