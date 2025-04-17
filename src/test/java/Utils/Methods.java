package Utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.File;
import java.io.FileReader;

public class Methods
{
    RequestSpecification httprequest = null;
    Response response = null;

    public void validateStatusCode(int code,Response response)
    {
       String Expected_Status_Code= Integer.toString(code);
        System.out.println("Expected Code"+ Expected_Status_Code);
        System.out.println("Actual Code"+response.getStatusLine() );
       Assert.assertTrue(response.getStatusLine().contains(Expected_Status_Code));
        System.out.println("Assertion Pass means Code Matching");
    }

    public JsonObject createJSONOBJ(String arg1)
    {
        JsonObject jsonObject=  new JsonParser().parse(arg1).getAsJsonObject();
        return jsonObject;
    }

    public String readJsonAsString(String path) throws Exception
    {
        JsonParser parser = new JsonParser();
        File file = new File(path);
        FileReader fileReader= new FileReader(file);
        Object obj = parser.parse(fileReader);
        return obj.toString();
    }

    public Response sendPostRequest(String baseURI,String requestPath,JsonObject body)
    {
        System.out.println(baseURI);
        RestAssured.baseURI = baseURI; // We are replacing host url to base url that we need to append in api hitting
        httprequest= RestAssured.given().relaxedHTTPSValidation();
        httprequest= httprequest.header("Content-Type","application/json");
        httprequest= httprequest.body(body.toString());
        response= httprequest.post(requestPath);
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
        return response;
    }
    public Response sendPutRequest(String baseURI,String requestPath,JsonObject body)
    {
        System.out.println(baseURI);
        RestAssured.baseURI = baseURI; // We are replacing host url to base url that we need to append in api hitting
        httprequest= RestAssured.given().relaxedHTTPSValidation();
        httprequest= httprequest.header("Content-Type","application/json");
        httprequest= httprequest.body(body.toString());
        response= httprequest.put(requestPath);
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
        return response;
    }
    public Response sendDeleteRequest(String baseURI,String requestPath)
    {
        System.out.println(baseURI);
        RestAssured.baseURI = baseURI; // We are replacing host url to base url that we need to append in api hitting
        httprequest= RestAssured.given().relaxedHTTPSValidation();
        httprequest= httprequest.header("Content-Type","application/json");
        response= httprequest.delete(requestPath);
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
        return response;
    }
}
