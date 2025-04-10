package Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

	Properties properties = new Properties();

	public ConfigLoader(String configFilePath) {
        try {
            FileInputStream input = new FileInputStream(configFilePath);
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
