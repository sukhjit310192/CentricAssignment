package controllerTest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


public class ApiIntegrationTest {


    private String baseURLProduct="/v1/products";
    String name = null;
    String description="";
    String brand="";
    String category="";
    List<String> tags=new ArrayList<String>();
    String product="";

    @Before
    public void setUp() throws JSONException, JsonMappingException, JsonProcessingException
    {
        Date date= new Date();
        description="Red hugo boss shirt"+date.getTime();	;
        name="Red Shirt"+date.getTime();
        brand="Hugo Boss"+date.getTime();	;
        category="apparel"+date.getTime();	;
        tags.add("red"+date.getTime());
        tags.add("shirt"+date.getTime());
        tags.add("slim fit"+date.getTime());
        product = "{\n" +
                "	\"name\": "+"\""+name+"\""+",\n" +
                "	\"description\": "+"\""+description+"\""+",\n" +
                "	\"brand\": "+"\""+brand+"\""+",\n" +
                "	\"tags\": [\n" +
                "				"+"\""+tags.get(0)+"\""+",\n" +
                "				"+"\""+tags.get(1)+"\""+",\n" +
                "				"+"\""+tags.get(2)+"\""+"\n" +
                "	],\n" +
                "	\"category\":"+"\""+category+"\""+"\n" +
                "}";
        PostTest();
    }

    public void PostTest() throws JsonMappingException, JsonProcessingException, JSONException {


        Response ProductResponse = given()
                .contentType("application/json")
                .body(product)
                .when().post(baseURLProduct)
                .then().statusCode(201).extract().response();

        JSONObject jsonObject= new JSONObject(ProductResponse.getBody().asString());

        assertEquals(jsonObject.get("name"), name);
        assertEquals(jsonObject.get("category"), category);
        assertEquals(jsonObject.get("description"), description);
        assertEquals(jsonObject.get("brand"), brand);

    }
    @Test
    public void getTest() throws JsonParseException, JsonMappingException, IOException, JSONException
    {
        given().
                when().
                get(baseURLProduct).
                then().body(containsString("\"name\":"+"\""+name+"\""))
                .body(containsString("\"description\":"+"\""+description+"\""))
                .body(containsString("\"brand\":"+"\""+brand+"\""))
                .body(containsString("\"category\":"+"\""+category+"\"")).statusCode(200);

    }
    @Test
    public void searchTest() throws JsonParseException, JsonMappingException, IOException, JSONException
    {
        String url=baseURLProduct+"/search"+"?"+"category"+"="+category;
        given().
                when().
                get(url).
                then().body(containsString("\"name\":"+"\""+name+"\""))
                .body(containsString("\"description\":"+"\""+description+"\""))
                .body(containsString("\"brand\":"+"\""+brand+"\""))
                .body(containsString("\"category\":"+"\""+category+"\"")).statusCode(302);

    }
    @After
    public void destroy()
    {


    }
}
