package command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EchoNodeTest {
    private final EchoNode echoNode = new EchoNode();

    @Test
    void testEmptyInputString() {
        String text = null;
        Long id = 123L;
        String result = echoNode.doCommand(text, id).getText();
        String equalResult =
                "Введите что-нибудь, чтобы бот вывел то же самое)";
        Assertions.assertEquals(result, equalResult);
    }

    @Test
    void testNotEmptyString() {
        String text = "Привет, я пытаюсь протестировать это и не понимаю, нужно ли оно здесь...";
        Long id = 123L;
        String result = echoNode.doCommand(text, id).getText();
        Assertions.assertEquals(result, text);
    }
}
