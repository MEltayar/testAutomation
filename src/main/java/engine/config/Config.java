package engine.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Reads settings from config.properties (on the classpath) once, at startup.
public class Config {
    private static final Properties PROPS = load();

    private static Properties load() {
        Properties p = new Properties();
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new IllegalStateException("config.properties not found on the classpath");
            }
            p.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read config.properties", e);
        }
        return p;
    }

    public static String browser() {
        return PROPS.getProperty("browser", "chrome");
    }

    public static int timeout() {
        return Integer.parseInt(PROPS.getProperty("timeout", "5"));
    }

    public static int pollingMillis() {
        return Integer.parseInt(PROPS.getProperty("pollingMillis", "250"));
    }

    public static boolean autoServeReport() {
        return Boolean.parseBoolean(PROPS.getProperty("autoServeReport", "false"));
    }

    // Generic lookup for app-specific keys (e.g. "sauceDemoBaseURL").
    public static String get(String key) {
        String value = PROPS.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing config key: " + key);
        }
        return value;
    }
}
