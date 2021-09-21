package ornaments.Config.wrappers;

import com.google.common.base.CaseFormat;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import net.minecraft.text.TranslatableText;

public class LocalizedConfigInteger extends ConfigInteger {
    private final String nameKey;

    public LocalizedConfigInteger(String modid, String key, int defaultValue, int minValue, int maxValue) {
        this(modid, key, defaultValue, minValue, maxValue, false);
    }

    public LocalizedConfigInteger(String modid, String key, int defaultValue, int minValue, int maxValue, boolean useSlider) {
        super(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key),
                defaultValue, minValue, maxValue, useSlider, "config." + modid + "." + key + ".desc");
        this.nameKey = "config." + modid + "." + key;
    }

    @Override
    public String getConfigGuiDisplayName() {
        return new TranslatableText(nameKey).getString();
    }
}
