package common;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import command.HandlerNode;

public class WorkWithMessage {
	private final HandlerNode handler = new HandlerNode();

	public WorkWithMessage() {}

	public SendMessage handleMessage(String text) {
		SendMessage sendMessage = new SendMessage();
		if (text.startsWith("/")) {
			String answerAboutCommand = handler.checkCommand(text);
			if (answerAboutCommand != null) {
				sendMessage.setText(answerAboutCommand);
				return sendMessage;
			} else {
				sendMessage.setText("Такой команды нет, напишите /help чтобы узнать доступные команды");
				return sendMessage;
			}
		}
		else {
			sendMessage.setText(text);
			return sendMessage;
		}
	}
}
