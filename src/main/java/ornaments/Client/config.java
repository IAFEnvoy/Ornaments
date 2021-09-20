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
    private static float r1 = 0, g1 = 0, b1 = 0,r2=0,g2=0,b2=0;
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
                r1 = Float.parseFloat(br.readLine());
                g1 = Float.parseFloat(br.readLine());
                b1 = Float.parseFloat(br.readLine());
                r2 = Float.parseFloat(br.readLine());
                g2 = Float.parseFloat(br.readLine());
                b2 = Float.parseFloat(br.readLine());
                hidewithe = Boolean.parseBoolean(br.readLine());
            } catch (IOException e) {
              playername="";
                capeUserName = "///";
                wingtype = "";
                r1 = 0;
                g1 = 0;
                b1 = 0;
                r2 = 0;
                g2 = 0;
                b2 = 0;
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

    public static float getR1() {
        return config.r1;
    }

    public static float getG1() {
        return config.g1;
    }

    public static float getB1() {
        return config.b1;
    }
    public static float getR2() {
        return config.r2;
    }

    public static float getG2() {
        return config.g2;
    }

    public static float getB2() {
        return config.b2;
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

    public static void setR1(float in) {
        config.r1 = in;
    }

    public static void setG1(float in) {
        config.g1 = in;
    }

    public static void setB1(float in) {
        config.b1 = in;
    }

    public static void setR2(float in) {
        config.r2 = in;
    }

    public static void setG2(float in) {
        config.g2 = in;
    }

    public static void setB2(float in) {
        config.b2 = in;
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
            sBuffer.append(String.valueOf(r1) + "\n");
            sBuffer.append(String.valueOf(g1) + "\n");
            sBuffer.append(String.valueOf(b1) + "\n");
            sBuffer.append(String.valueOf(r2) + "\n");
            sBuffer.append(String.valueOf(g2) + "\n");
            sBuffer.append(String.valueOf(b2) + "\n");
            sBuffer.append(String.valueOf(hidewithe) + "\n");
            bw.write(sBuffer.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
