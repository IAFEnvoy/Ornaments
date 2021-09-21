package ornaments.Data;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class SpliceableText {
  public static Text makeText(String key, String arg){
    String temp=(new TranslatableText(key)).getString();
    return Text.of(temp.replace("\u0000", arg));
  }
  public static Text makeText(String key, String arg1,String arg2){
    String temp=(new TranslatableText(key)).getString();
    return Text.of(temp.replace("\u0000", arg1).replace("\u1111", arg2));
  }
}
