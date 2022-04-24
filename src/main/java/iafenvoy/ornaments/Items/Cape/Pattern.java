package iafenvoy.ornaments.Items.Cape;

import fi.dy.masa.malilib.util.Color4f;

public class Pattern {
  private final String id;
  private final Color4f color;

  public Pattern(String id, Color4f color) {
    this.id = id;
    this.color = color;
  }

  public String getPatternID() {
    return this.id;
  }

  public Color4f getColor() {
    return this.color;
  }
}
