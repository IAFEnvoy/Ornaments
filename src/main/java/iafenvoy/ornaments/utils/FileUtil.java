package iafenvoy.ornaments.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class FileUtil {
    public static void writeFile(String path, List<String> data) {
        try {
            FileWriter writer = new FileWriter(path);
            for (String str : data)
                writer.write(str + "\r\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String path) {
        StringBuilder data = new StringBuilder();
        try {
            FileReader fr = new FileReader(path);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            while ((str = bf.readLine()) != null)
                data.append(str);
            bf.close();
            fr.close();
        } catch (Exception e) {
            data = new StringBuilder();
            e.printStackTrace();
        }
        return data.toString();
    }
}
