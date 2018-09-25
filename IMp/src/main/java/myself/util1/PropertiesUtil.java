package myself.util1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static Properties prop;
    static {
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.properties");
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getStringValue(String key){
        String v = prop.getProperty(key);
        return v;
    }
    public static int getintValue(String key){
        int v = Integer.parseInt(prop.getProperty(key));
        return v;
    }
    public static Boolean getBooleanValue(String key){
        boolean v = prop.getProperty(key).equals(true);
        return v;
    }
}
