package ornaments.Config.wrappers;

import com.google.common.base.CaseFormat;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import net.minecraft.text.TranslatableText;

public class LocalizedConfigHotkey extends ConfigHotkey {
    private final String nameKey;

    public LocalizedConfigHotkey(String modid, String key, String defaultStorageString) {
        super(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key),
                defaultStorageString, "config." + modid + "." + key + ".desc");
        this.nameKey = "config." + modid + "." + key;
    }

    @Override
    public String getConfigGuiDisplayName() {
        return new TranslatableText(nameKey).getString();
    }
}
