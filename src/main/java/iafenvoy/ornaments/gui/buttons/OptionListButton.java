package iafenvoy.ornaments.gui.buttons;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import iafenvoy.ornaments.gui.ValueChangeCallback;

public class OptionListButton extends ButtonGeneric implements LocatableButton<IConfigOptionListEntry, IButtonActionListener> {
    private final String id;
    private IConfigOptionListEntry entry;

    public OptionListButton(String id, int width, IConfigOptionListEntry defaultValue) {
        super(0, 0, width, false, defaultValue.getDisplayName());
        this.id = id;
        this.entry = defaultValue;
    }

    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public IButtonActionListener getCallback(ValueChangeCallback callback) {
        return (button, mouseButton) -> {
            this.entry = this.entry.cycle(mouseButton == 0);
            this.displayString = this.entry.getDisplayName();
            if (callback != null) callback.onValueChange();
        };
    }

    @Override
    public IConfigOptionListEntry getValue() {
        return this.entry;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
