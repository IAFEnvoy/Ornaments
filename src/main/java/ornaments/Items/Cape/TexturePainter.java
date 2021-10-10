package ornaments.Items.Cape;

import java.util.HashMap;
import java.util.List;

import com.mojang.datafixers.util.Pair;

import fi.dy.masa.malilib.util.Color4f;
import net.minecraft.util.Identifier;

public class TexturePainter {
  private static HashMap<String, Identifier> capes = new HashMap<String, Identifier>();
  public static Identifier getTexture(BannerInfo info){
    List<Pair<String, Color4f>> data=info.getPatterns();
    String name="";
    for(int i=0;i<data.size();i++){
      name=data.get(i).getFirst()+data.get(i).getSecond().toString();
    }
    if(capes.get(name)!=null) return capes.get(name);
    return new Identifier("","");
  }
}
