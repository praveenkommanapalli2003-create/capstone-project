package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private static Properties properties;
    private static final String CONFIG_PATH = 
        "src/main/resources/config.properties";

    // Load properties once when class is first used
    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_PATH);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("config.properties file not found: " 
                + e.getMessage());
        }
    }

    // Get any value by key
    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key '" + key + 
                "' not found in config.properties");
        }
        return value.trim();
    }

    // Specific helper methods
    public static String getBrowser() {
        return get("browser");
    }

    public static String getURL() {
        return get("url");
    }

    public static String getUsername() {
        return get("username");
    }

    public static String getPassword() {
        return get("password");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(get("implicit.wait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(get("explicit.wait"));
    }
}