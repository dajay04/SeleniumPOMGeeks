package tests;

import Utils.Methods;
import base.BaseTest;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.Constants;
import utils.PayloadKeys;
import utils.RunTimeVariable;

public class DeleteUserAPITest extends BaseTest
{
    Methods methods = new Methods();
    String requestPath;
    Response response;

    @Test(priority = 1)
    public void getResponse()
    {
        try
        {
            requestPath = BASE_URL + Constants.DELETE_USER_URI;
            requestPath = requestPath.replace("ID",RunTimeVariable.ID);
            System.out.println("Compelte Url for Hititng API:" + requestPath);
            response = methods.sendDeleteRequest(BASE_URL, requestPath);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
