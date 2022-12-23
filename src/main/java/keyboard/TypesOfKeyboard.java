package keyboard;

import java.util.HashMap;

public enum TypesOfKeyboard {
    HELP_KEYBOARD(new HashMap<String, String>() {{
        put("Инфа об about", "about");
        put("Инфа о weather", "weather");
        put("Инфа об echo", "echo");
        put("Инфа о help", "help");
    }});

    private final HashMap<String, String> buttons;

    TypesOfKeyboard(HashMap<String, String> buttons) {
        this.buttons = buttons;
    }

    public HashMap<String, String> getButtons() {
        return this.buttons;
    }
}
