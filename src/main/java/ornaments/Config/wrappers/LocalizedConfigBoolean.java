package ornaments.Config.wrappers;

import com.google.common.base.CaseFormat;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import net.minecraft.text.TranslatableText;

public class LocalizedConfigBoolean extends ConfigBoolean {
    private final String nameKey;

    public LocalizedConfigBoolean(String modid, String key, boolean defaultValue) {
        super(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key),
                defaultValue, "config." + modid + "." + key + ".desc");
        this.nameKey = "config." + modid + "." + key;
    }

    @Override
    public String getConfigGuiDisplayName() {
        return new TranslatableText(nameKey).getString();
    }
}
