package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

private static Properties properties = new Properties();

static {
    try {
        properties.load(new FileInputStream("src/test/resources/config.properties"));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

    public static String getProperties(String key) {
        return properties.getProperty(key);
    }
}
