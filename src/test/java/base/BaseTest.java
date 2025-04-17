package base;

import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest
{
    public static String BASE_URL;
    public static long randomNumber;

    @BeforeMethod
    public void setUp()
    {
        BASE_URL=ConfigReader.getProperty("reqURL");
        randomNumber = (long) (Math.random()*10000 + 33333300000L); // 1263126371273
    }

}
