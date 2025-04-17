package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader
{
    static Properties properties;

    static
    {
        try
        {
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties");
            properties = new Properties();
            properties.load(inputStream);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key)
    {
        return properties.getProperty(key);
    }
}
