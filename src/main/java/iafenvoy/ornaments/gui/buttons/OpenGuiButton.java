package iafenvoy.ornaments.gui.buttons;

import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import iafenvoy.ornaments.gui.ValueChangeCallback;

public class OpenGuiButton extends ButtonGeneric implements LocatableButton<GuiBase, IButtonActionListener> {
    private final GuiBase gui;
    private final String id;

    public OpenGuiButton(String id, String translationKey, int width, GuiBase gui) {
        super(0, 0, width, false, translationKey);
        this.id = id;
        this.gui = gui;
    }

    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public IButtonActionListener getCallback(ValueChangeCallback callback) {
        return (button, mouseButton) -> {
            GuiBase.openGui(gui);
            if (callback != null) callback.onValueChange();
        };
    }

    @Override
    public GuiBase getValue() {
        return this.gui;
    }

    @Override
    public String getId() {
        return this.id;
    }
}

