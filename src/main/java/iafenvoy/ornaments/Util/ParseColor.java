package iafenvoy.ornaments.Util;

import fi.dy.masa.malilib.util.Color4f;

public class ParseColor {
  public static Color4f parse(String color) {
    float a = to10(color.charAt(1)) * 16 + to10(color.charAt(2));
    float r = to10(color.charAt(3)) * 16 + to10(color.charAt(4));
    float g = to10(color.charAt(5)) * 16 + to10(color.charAt(6));
    float b = to10(color.charAt(7)) * 16 + to10(color.charAt(8));
    return new Color4f(r / 256, g / 256, b / 256, a / 256);
  }

  private static int to10(char from16) {
    switch (from16) {
    case '0':
      return 0;
    case '1':
      return 1;
    case '2':
      return 2;
    case '3':
      return 3;
    case '4':
      return 4;
    case '5':
      return 5;
    case '6':
      return 6;
    case '7':
      return 7;
    case '8':
      return 8;
    case '9':
      return 9;
    case 'A':
      return 10;
    case 'B':
      return 11;
    case 'C':
      return 12;
    case 'D':
      return 13;
    case 'E':
      return 14;
    case 'F':
      return 15;
    default:
      return 0;
    }
  }
}
