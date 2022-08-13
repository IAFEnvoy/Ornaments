package iafenvoy.ornaments.gui.buttons;

import fi.dy.masa.malilib.gui.GuiTextFieldGeneric;
import fi.dy.masa.malilib.gui.interfaces.ITextFieldListener;
import iafenvoy.ornaments.gui.ValueChangeCallback;
import net.minecraft.client.MinecraftClient;

public class TextField extends GuiTextFieldGeneric implements LocatableButton<String, ITextFieldListener<TextField>> {
    private static final MinecraftClient client = MinecraftClient.getInstance();
    private final String id;

    public TextField(String id, int width, String defaultValue) {
        super(0, 0, width, 15, client.textRenderer);
        this.id = id;
        this.setTextPredicate(s -> true);
        this.setText(defaultValue);
    }

    public TextField(String id, int x, int y, int width, String defaultValue) {
        super(x + 2, y + 2, width - 4, 15, client.textRenderer);
        this.id = id;
        this.setTextPredicate(s -> true);
        this.setText(defaultValue);
    }

    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public ITextFieldListener<TextField> getCallback(ValueChangeCallback callback) {
        return textField -> {
            this.setText(textField.getText());
            if (callback != null) callback.onValueChange();
            return true;
        };
    }

    @Override
    public String getValue() {
        return this.getText();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public String getId() {
        return this.id;
    }
}
