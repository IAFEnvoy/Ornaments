package iafenvoy.ornaments.gui.buttons;

import iafenvoy.ornaments.gui.ValueChangeCallback;

public interface LocatableButton<T, L> {
    void setLocation(int x, int y);

    L getCallback(ValueChangeCallback callback);

    T getValue();

    String getId();
}
