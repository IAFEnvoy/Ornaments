package ornaments.Config.wrappers;

import com.google.common.base.CaseFormat;
import fi.dy.masa.malilib.config.options.ConfigDouble;
import net.minecraft.text.TranslatableText;

public class LocalizedConfigDouble extends ConfigDouble {
    private final String nameKey;

    public LocalizedConfigDouble(String modid, String key, double defaultValue, double minValue, double maxValue) {
        this(modid, key, defaultValue, minValue, maxValue, false);
    }

    public LocalizedConfigDouble(String modid, String key, double defaultValue, double minValue, double maxValue, boolean useSlider) {
        super(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key),
                defaultValue, minValue, maxValue, useSlider, "config." + modid + "." + key + ".desc");
        this.nameKey = "config." + modid + "." + key;
    }

    @Override
    public String getConfigGuiDisplayName() {
        return new TranslatableText(nameKey).getString();
    }
}
