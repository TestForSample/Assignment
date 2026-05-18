package base;

import  io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected final String BASE_URL =
            "https://staging.agechecked.com/api/acapiremote/ac0101";

    protected static final String API_KEY =
            "Cm9U48CxDuG7ntq3TAWJvGKJtkKnLvPwijb3nVg329Ly8ipafLcAx0s035y3x12f";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }
}
