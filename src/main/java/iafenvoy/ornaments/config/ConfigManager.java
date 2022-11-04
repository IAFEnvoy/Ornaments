package iafenvoy.ornaments.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import iafenvoy.ornaments.renderer.cape.PhysicalRenderer;
import iafenvoy.ornaments.utils.FileUtil;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;

public class ConfigManager {
    public static final ConfigManager INSTANCE = new ConfigManager();
    private static final String FILE_PATH = "./config/ornaments.json";
    private static final File CONFIG_DIR = new File("./config");
    private final HashMap<String, Config> configs = new HashMap<>();

    public ConfigManager() {
        configs.put("cape", CapeInfo.cape);
        configs.put("leftElytra", CapeInfo.leftElytra);
        configs.put("rightElytra", CapeInfo.rightElytra);
        configs.put("wing", WingInfo.wing);
        configs.put("leftBackTool", ToolInfo.leftBackTool);
        configs.put("rightBackTool", ToolInfo.rightBackTool);
        configs.put("leftBeltTool", ToolInfo.leftBeltTool);
        configs.put("rightBeltTool", ToolInfo.rightBeltTool);
        configs.put("leftPlayer", ShoulderPlayerInfo.leftPlayer);
        configs.put("rightPlayer", ShoulderPlayerInfo.rightPlayer);
    }

    public void load() {
        File file = new File(FILE_PATH);
        if (file.isFile() && file.exists()) {
            try {
                JsonObject json = new JsonParser().parse(FileUtil.readFile(FILE_PATH)).getAsJsonObject();
                if (json.has("physical"))
                    PhysicalRenderer.use = json.get("physical").getAsBoolean();
                for (String key : configs.keySet())
                    if (json.has(key))
                        try {
                            configs.get(key).copy(json.get(key).getAsString());
                        } catch (JsonSyntaxException e) {
                            System.out.println("Failed to load config: " + key);
                            throw e;
                        }
                    else
                        System.out.println("Can't find config: " + key + " ,generate new one");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void save() {
        if ((CONFIG_DIR.exists() && CONFIG_DIR.isDirectory()) || CONFIG_DIR.mkdirs())
            try {
                JsonObject json = new JsonObject();
                json.addProperty("physical", PhysicalRenderer.use);
                for (String key : configs.keySet())
                    json.addProperty(key, configs.get(key).toJson());
                FileUtil.writeFile(FILE_PATH, Collections.singletonList(json.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
