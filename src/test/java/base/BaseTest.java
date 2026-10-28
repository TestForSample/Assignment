package base;

import  io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected final String BASE_URL =
            "https://staging.testing.com/api/";

    protected static final String API_KEY =
            "abcAbc00001";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }
}
