package uitests.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProvider {
    private static ConfigProvider instance;
    private Properties properties;

    ConfigProvider() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src/main/resources/config.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigProvider getInstance() {
        if (instance == null) {
            instance = new ConfigProvider();
        }
        return instance;
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}
