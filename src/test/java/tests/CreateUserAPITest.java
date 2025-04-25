package tests;

import Utils.Methods;
import base.BaseTest;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.Constants;
import utils.PayloadKeys;
import utils.RunTimeVariable;

public class CreateUserAPITest extends BaseTest
{
    Methods methods = new Methods();
    String requestBody;
    JsonObject body;
    String requestPath;
    Response response;

    @Parameters({ "PayloadPath" })
    @Test(priority = 1)
    public void selectPayload(String PayloadPath) throws Exception
    {
        try
        {
            this.requestBody = methods.readJsonAsString(PayloadKeys.PAYLOAD_CREATE_USER + PayloadPath + ".json")
                    .replace("morpheus", "John" + randomNumber);

            System.out.println("The Payload seelcted is" + this.requestBody);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test(dependsOnMethods = {"selectPayload"},priority = 2)
    public void getResponse()
    {
        try
        {
            body = methods.createJSONOBJ(this.requestBody);
            requestPath = BASE_URL + Constants.CREATE_USER_URI;
            System.out.println("Compelte Url for Hititng API:" + requestPath);
            response = methods.sendPostRequest(BASE_URL, requestPath, body);
            if(response.getStatusCode()==201)
            {
                storingResposneData(response);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Parameters({ "Code" })
    @Test(dependsOnMethods = {"getResponse"},priority = 3)
    public void validateResponseCode(int Code)
    {
        try
        {
            methods.validateStatusCode(Code, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Test(dependsOnMethods = {"getResponse"},priority = 4)
    public void validateResponseMessage()
    {
       JsonPath evalutor= response.jsonPath();
       Assert.assertTrue(evalutor.get("id").toString().contains(RunTimeVariable.ID));
    }

    //5. If i want to store some data ... */
    public void storingResposneData(Response response)
    {
        JsonPath evalutor= response.jsonPath();
        RunTimeVariable.ID= evalutor.get("id");
        RunTimeVariable.NAME= evalutor.get("name");
    }
}
