package iafenvoy.ornaments.utils;

import fi.dy.masa.malilib.util.Color4f;
import iafenvoy.ornaments.gui.buttons.LocatableButton;

public class MathUtil {
    public static boolean between(double value, double min, double max) {
        return value >= min && value <= max;
    }

    public static float parseToFloat(LocatableButton<?, ?> button) {
        String text = (String) button.getValue();
        try {
            return (float) Double.parseDouble(text);
        } catch (Exception e) {
            return 0;
        }
    }

    public static Color4f parse(String color) throws NumberFormatException {
        if (color == null) throw new NumberFormatException();
        if (color.length() == 0) throw new NumberFormatException();
        if (color.charAt(0) == '#') color = color.substring(1);
        if (color.length() == 6) {
            float r = Integer.parseInt(color.substring(0, 2), 16);
            float g = Integer.parseInt(color.substring(2, 4), 16);
            float b = Integer.parseInt(color.substring(4, 6), 16);
            return new Color4f((float) (r / 255.0), (float) (g / 255.0), (float) (b / 255.0));
        } else if (color.length() == 8) {
            float a = Integer.parseInt(color.substring(0, 2), 16);
            float r = Integer.parseInt(color.substring(2, 4), 16);
            float g = Integer.parseInt(color.substring(4, 6), 16);
            float b = Integer.parseInt(color.substring(6, 8), 16);
            return new Color4f((float) (r / 255.0), (float) (g / 255.0), (float) (b / 255.0), (float) (a / 255.0));
        } else
            throw new NumberFormatException();
    }

    public static String toString(Color4f color) {
        String ret = "#";
        ret += toTwoDigit(Integer.toHexString((int) (color.a * 255.0)));
        ret += toTwoDigit(Integer.toHexString((int) (color.r * 255.0)));
        ret += toTwoDigit(Integer.toHexString((int) (color.g * 255.0)));
        ret += toTwoDigit(Integer.toHexString((int) (color.b * 255.0)));
        return ret;
    }

    private static String toTwoDigit(String value) {
        StringBuilder valueBuilder = new StringBuilder(value);
        while (valueBuilder.length() < 2) valueBuilder.insert(0, "0");
        return valueBuilder.toString();
    }
}
