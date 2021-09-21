package ornaments.Config.wrappers;

import com.google.common.base.CaseFormat;
import fi.dy.masa.malilib.config.options.ConfigString;
import net.minecraft.text.TranslatableText;

public class LocalizedConfigString extends ConfigString {
    private final String nameKey;

    public LocalizedConfigString(String modid, String key, String defaultValue) {
        super(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key),
                defaultValue, "config." + modid + "." + key + ".desc");
        this.nameKey = "config." + modid + "." + key;
    }

    @Override
    public String getConfigGuiDisplayName() {
        return new TranslatableText(nameKey).getString();
    }
}
