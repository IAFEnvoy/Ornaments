package ornaments.multiplayer;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;

public enum DataBaseURL implements IConfigOptionListEntry {
    Default( "https://iafenvoy.github.io/DataBase/OrnamentsStorage/%s/%s", "config.url.default"),
    CDN("https://cdn.jsdelivr.net/gh/IAFEnvoy/OrnamentsStorage@latest/%s/%s", "config.url.cdn");

    private final String configString;
    private final String translationKey;

    private DataBaseURL(String configString, String translationKey) {
        this.configString = configString;
        this.translationKey = translationKey;
    }
    @Override
    public String getStringValue() {
        return this.configString;
    }

    @Override
    public String getDisplayName() {
        return StringUtils.translate(this.translationKey);
    }

    @Override
    public DataBaseURL cycle(boolean forward) {
        int id = this.ordinal();

        if (forward) {
            if (++id >= values().length) {
                id = 0;
            }
        } else {
            if (--id < 0) {
                id = values().length - 1;
            }
        }
        return values()[id % values().length];
    }

    @Override
    public DataBaseURL fromString(String name) {
        return fromStringStatic(name);
    }

    public static DataBaseURL fromStringStatic(String name) {
        for (DataBaseURL val : DataBaseURL.values())
            if (val.configString.equalsIgnoreCase(name))
                return val;
        return DataBaseURL.Default;
    }
}
