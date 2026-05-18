package payload;

import base.BaseTest;
import org.json.JSONObject;

public class PayloadManager extends BaseTest {
    public static JSONObject getValidPayload() {

        JSONObject payload = new JSONObject();

        payload.put("merchantSecretKey",API_KEY);
        payload.put("name", "John");
        payload.put("surname", "COMPLETELYFOUND");
        payload.put("building", "10");
        payload.put("street", "Downing Street");
        payload.put("postcode", "SW1A2AA");
        payload.put("dob", "15/06/1967");
        payload.put("countrycode", "GB");
        payload.put("email", "John123456@email.com");

        return payload;
    }
}
