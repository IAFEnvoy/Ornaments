package ornaments.Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class config {
    private static String capeUserName = "";
    private static String wingtype = "";
    private static float r = 0, g = 0, b = 0;
    private static String configpath = "\\config\\ornaments.config";
    private static Boolean hidewithe = true;
    public static String playername="";

    public static void init() {
        File f = new File("");
        String cf = null;
        try {
            cf = f.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        configpath = cf + configpath;
    }

    public static void readConfig() {
        File file = new File(configpath);
        if (!file.exists())
            return;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(configpath));
            try {
                playername = br.readLine();
                capeUserName = br.readLine();
                wingtype = br.readLine();
                r = Float.parseFloat(br.readLine());
                g = Float.parseFloat(br.readLine());
                b = Float.parseFloat(br.readLine());
                hidewithe = Boolean.parseBoolean(br.readLine());
            } catch (IOException e) {
              playername="";
                capeUserName = "///";
                wingtype = "";
                r = 0;
                g = 0;
                b = 0;
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getWingType() {
        return config.wingtype;
    }

    public static String getCape() {
        return config.capeUserName;
    }

    public static float getR() {
        return config.r;
    }

    public static float getG() {
        return config.g;
    }

    public static float getB() {
        return config.b;
    }

    public static Boolean getHide() {
        return config.hidewithe;
    }

    public static void setWingType(String in) {
        config.wingtype = in;
    }

    public static void setCape(String in) {
        config.capeUserName = in;
    }

    public static void setR(float in) {
        config.r = in;
    }

    public static void setG(float in) {
        config.g = in;
    }

    public static void setB(float in) {
        config.b = in;
    }

    public static void setHide(Boolean in) {
        config.hidewithe = in;
    }

    public static void saveConfig() {
        try {
            File file = new File(configpath);
            if (!file.exists())
                file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(configpath, false));
            StringBuffer sBuffer = new StringBuffer("");
            sBuffer.append(playername + "\n");
            sBuffer.append(capeUserName + "\n");
            sBuffer.append(wingtype + "\n");
            sBuffer.append(String.valueOf(r) + "\n");
            sBuffer.append(String.valueOf(g) + "\n");
            sBuffer.append(String.valueOf(b) + "\n");
            sBuffer.append(String.valueOf(hidewithe) + "\n");
            bw.write(sBuffer.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
