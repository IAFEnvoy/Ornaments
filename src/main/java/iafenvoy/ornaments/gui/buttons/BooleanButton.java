package iafenvoy.ornaments.gui.buttons;

import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import iafenvoy.ornaments.gui.ValueChangeCallback;

public class BooleanButton extends ButtonGeneric implements LocatableButton<Boolean, IButtonActionListener> {
    private final String id;
    private boolean value;

    public BooleanButton(String id, int width, boolean defaultValue) {
        super(0, 0, width, false, (defaultValue ? GuiBase.TXT_GREEN : GuiBase.TXT_RED) + defaultValue);
        this.id = id;
        this.value = defaultValue;
    }

    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public IButtonActionListener getCallback(ValueChangeCallback callback) {
        return (button, mouseButton) -> {
            this.value ^= true;
            this.displayString = (this.value ? GuiBase.TXT_GREEN : GuiBase.TXT_RED) + this.value;
            if (callback != null) callback.onValueChange();
        };
    }

    @Override
    public Boolean getValue() {
        return this.value;
    }

    @Override
    public String getId() {
        return this.id;
    }
}