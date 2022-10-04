package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    static Properties properties;

    public static Properties readProperties(String filepath) {
        try {
            FileInputStream fis = new FileInputStream(filepath);
            properties = new Properties();
            properties.load(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getPropertyValue(String pathToPropertyFile, String key) {
        return readProperties(pathToPropertyFile).getProperty(key);
    }
}
