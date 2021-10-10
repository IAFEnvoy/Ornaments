package ornaments.Items.Cape;

import java.util.ArrayList;
import java.util.List;

import com.mojang.datafixers.util.Pair;

import fi.dy.masa.malilib.util.Color4f;

public class BannerInfo {
  private List<Pair<String, Color4f>> patterns = new ArrayList<>();

  public BannerInfo(Color4f basecolor, Pattern... patterns) {
    this.patterns.add(new Pair<String, Color4f>("base", basecolor));
    for (Pattern pattern : patterns) {
      this.patterns.add(new Pair<String, Color4f>(pattern.getPatternID(),
          pattern.getColor()));
    }
  }
  public List<Pair<String, Color4f>> getPatterns(){
    return this.patterns;
  }

  public BannerInfo() {
  }
}
