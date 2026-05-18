package testclass;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.PayloadManager;

import static io.restassured.RestAssured.given;

    public class AC0101Test extends BaseTest {

        @Test(priority = 1)
        public void verifyWrongEndpoint() {

            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .body(PayloadManager.getValidPayload().toString())
                            .when()
                            .post("/wrongendpoint");

            Assert.assertEquals(response.getStatusCode(), 404);
        }

        /*
         Verify merchantSecretKey missing
         */
        @Test(priority = 2)
        public void verifyMerchantKeyMissing() {

            JSONObject payload = PayloadManager.getValidPayload();

            payload.remove("merchantSecretKey");

            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .body(payload.toString())
                            .when()
                            .post();

            Assert.assertNotEquals(
                    response.jsonPath().getString("errormessage"),
                    null);
        }

        /*
         Verify merchantSecretKey empty
         */
        @Test(priority = 3)
        public void verifyMerchantKeyEmpty() {

            JSONObject payload = PayloadManager.getValidPayload();

            payload.put("merchantSecretKey", "");

            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .body(payload.toString())
                            .when()
                            .post();

            Assert.assertNotNull(
                    response.jsonPath().getString("errormessage"));
        }

        /*
         Verify invalid merchantSecretKey
         */
        @Test(priority = 4)
        public void verifyInvalidMerchantKey() {

            JSONObject payload = PayloadManager.getValidPayload();

            payload.put("merchantSecretKey", "INVALIDKEY");

            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .body(payload.toString())
                            .when()
                            .post();

            Assert.assertNotNull(
                    response.jsonPath().getString("errormessage"));
        }

        /*
         Verify surname NOTFOUND
         */
        @Test(priority = 5)
        public void verifyNotFoundStatus() {

            JSONObject payload = PayloadManager.getValidPayload();

            payload.put("surname", "NOTFOUND");

            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .body(payload.toString())
                            .when()
                            .post();

            Assert.assertEquals(
                    response.jsonPath().getInt("avstatus.status"),
                    16);

            Assert.assertEquals(
                    response.jsonPath().getString("avstatus.statusText"),
                    "NotRecognised");
        }

        /*
         Verify surname COMPLETELYFOUND
         */
        @Test(priority = 6)
        public void verifyApprovedStatus() {

            JSONObject payload = PayloadManager.getValidPayload();

            payload.put("surname", "COMPLETELYFOUND");

            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .body(payload.toString())
                            .when()
                            .post();

            Assert.assertEquals(
                    response.jsonPath().getInt("avstatus.status"),
                    6);

            Assert.assertEquals(
                    response.jsonPath().getString("avstatus.statusText"),
                    "Approved");
        }

        /*
         Verify missing name
         */
        @Test(priority = 7)
        public void verifyNameMissing() {

            JSONObject payload = PayloadManager.getValidPayload();

            payload.remove("name");

            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .body(payload.toString())
                            .when()
                            .post();

            Assert.assertNotNull(
                    response.jsonPath().getString("errormessage"));
        }

        /*
         Verify empty postcode
         */
        @Test(priority = 8)
        public void verifyPostcodeEmpty() {

            JSONObject payload = PayloadManager.getValidPayload();

            payload.put("postcode", "");

            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .body(payload.toString())
                            .when()
                            .post();

            Assert.assertNotNull(
                    response.jsonPath().getString("errormessage"));
        }

        /*
         Verify invalid DOB mismatch
         */
        @Test(priority = 9)
        public void verifyDOBMismatch() {

            JSONObject payload = PayloadManager.getValidPayload();

            payload.put("dob", "01/01/2005");

            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .body(payload.toString())
                            .when()
                            .post();

            Assert.assertEquals(
                    response.jsonPath().getInt("avstatus.status"),
                    13);

            Assert.assertEquals(
                    response.jsonPath().getString("avstatus.statusText"),
                    "ApprovedAndDOBMismatch");
        }

        /*
         Verify invalid country code
         */
        @Test(priority = 10)
        public void verifyInvalidCountryCode() {

            JSONObject payload = PayloadManager.getValidPayload();

            payload.put("countrycode", "XX");

            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .body(payload.toString())
                            .when()
                            .post();

            Assert.assertNotNull(
                    response.jsonPath().getString("errormessage"));
        }

        /*
         Verify invalid email format
         */
        @Test(priority = 11)
        public void verifyInvalidEmail() {

            JSONObject payload = PayloadManager.getValidPayload();

            payload.put("email", "John123456email.com");

            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .body(payload.toString())
                            .when()
                            .post();

            Assert.assertNotNull(
                    response.jsonPath().getString("errormessage"));
        }
    }

