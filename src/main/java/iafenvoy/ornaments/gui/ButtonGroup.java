package iafenvoy.ornaments.gui;

import com.ibm.icu.impl.Pair;
import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.gui.GuiTextFieldGeneric;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import fi.dy.masa.malilib.gui.interfaces.ITextFieldListener;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.gui.buttons.LocatableButton;
import iafenvoy.ornaments.gui.buttons.TextField;

import java.util.ArrayList;
import java.util.List;

public class ButtonGroup {
    private final int x, y;
    private final List<Property> properties = new ArrayList<>();
    private final int textOffset;
    private int flag = -1;
    private ValueChangeCallback callback = null;


    public ButtonGroup(int x, int y, int textOffset) {
        this.x = x;
        this.y = y;
        this.textOffset = textOffset;
    }

    public void setValueChangeCallback(ValueChangeCallback callback) {
        this.callback = callback;
    }

    public void newProperty(String textKey) {
        flag++;
        properties.add(new Property(textKey, flag * 20 + y));
    }

    public void add(LocatableButton<?, ?> button) {
        if (flag >= 0)
            this.properties.get(flag).add(button);
    }

    public void initGui(GuiBase gui) {
        for (Property p : this.properties)
            p.initGui(gui, textOffset + this.x + 10);
    }

    public LocatableButton<?, ?> getButtonById(String id) {
        for (Property p : this.properties)
            for (Pair<String, LocatableButton<?, ?>> button : p.buttons)
                if (button.first.equals(id))
                    return button.second;
        return null;
    }

// --注释掉检查 START (2022/8/9 23:44):
//    public boolean onKeyPress(int keyCode) {
//        boolean execute = false;
//        for (Property p : this.properties)
//            execute |= p.onKeyPress(keyCode);
//        return execute;
//    }
// --注释掉检查 STOP (2022/8/9 23:44)

    private class Property {
        private final String textKey;
        private final int offset;
        private final List<Pair<String, LocatableButton<?, ?>>> buttons = new ArrayList<>();

        public Property(String textKey, int offset) {
            this.textKey = textKey;
            this.offset = offset;
        }

        public void add(LocatableButton<?, ?> button) {
            this.buttons.add(Pair.of(button.getId(), button));
        }

        public void initGui(GuiBase gui, int textOffset) {
            gui.addLabel(x, this.offset, textOffset, 20, 0xFFFFFF, StringUtils.translate(this.textKey));
            int w = 0;
            for (Pair<String, LocatableButton<?, ?>> button : this.buttons) {
                if (button.second instanceof ButtonGeneric) {
                    button.second.setLocation(textOffset + w, this.offset);
                    gui.addButton((ButtonGeneric) button.second, (IButtonActionListener) button.second.getCallback(callback));
                    w += ((ButtonGeneric) button.second).getWidth();
                } else {
                    TextField field = new TextField(button.second.getId(), textOffset + w, this.offset, ((GuiTextFieldGeneric) button.second).getWidth(), (String) button.second.getValue());
                    gui.addTextField(field, (ITextFieldListener<TextField>) button.second.getCallback(callback));
                    w += field.getWidth();
                }
            }
        }

    }
}