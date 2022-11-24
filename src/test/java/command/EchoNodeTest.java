package command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EchoNodeTest {
	private final EchoNode echoNode = new EchoNode();

	@Test
	void testEmptyInputString() {
		String text = null;
		String result = echoNode.doCommand(text);
		String equalResult =
				"Введите что-нибудь после /echo для вывода на экран";
		Assertions.assertEquals(result, equalResult);
	}

	@Test
	void testNotEmptyString() {
		String text = "Привет, я пытаюсь протестировать это и не понимаю, нужно ли оно здесь...";
		String result = echoNode.doCommand(text);
		Assertions.assertEquals(result, text);
	}
}
