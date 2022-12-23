package common;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import command.HandlerNode;
import keyboard.KeyboardCreator;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import java.io.FilterOutputStream;

public class WorkWithMessage{
	private final KeyboardCreator keyboardCreator = new KeyboardCreator();
	private final HandlerNode handler = new HandlerNode();

	public WorkWithMessage() { }

	public SendMessage handleMessage(String text) {
		SendMessage sendMessage = new SendMessage();
		if (text.startsWith("/")) {
			SendMessage answerAboutCommand = handler.checkCommand(text);
			if (answerAboutCommand == null) {
				sendMessage.setText("Такой команды нет, напишите /help чтобы узнать доступные команды");
			}
			return answerAboutCommand;
		}
		sendMessage.setText(text);
		return sendMessage;
	}

}
