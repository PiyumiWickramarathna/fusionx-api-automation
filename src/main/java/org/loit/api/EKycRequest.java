package org.loit.api;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.loit.config.factory.ApiConfigFactory;
import org.loit.utils.DatabaseConnector;
import org.loit.utils.Utility;
import org.loit.api.assertwrapper.ResponseAssert;

public class EKycRequest {
    @Getter
    @Setter
    public String otp;

    @Getter
    @Setter
    public static String randomMobileNumber;

    Utility utils = new Utility();
    DatabaseConnector dbConnector = new DatabaseConnector();

    public void EKycRegistrationComplete(String accessToken, String CusRegistrationCompleteLocalPath, String randomMobileNumber) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().EKycRegistrationComplete();
        String cID = dbConnector.getCustomerId(randomMobileNumber);
        endpoint = endpoint.replace("{cID}", cID);
        JSONObject RegistrationCompleteLocal = (JSONObject) utils.jsonReader(CusRegistrationCompleteLocalPath);
        RegistrationCompleteLocal.put("mobileNo", randomMobileNumber);
        RegistrationCompleteLocal.put("cID", cID);

        Response response = ReqresApi.postRequest(RegistrationCompleteLocal, endpoint, accessToken);
        System.out.println("EKyc Registration Complete Response:" + response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .hasKeyWithValue("responseObject.phone", String.valueOf(RegistrationCompleteLocal.get("mobileNo")))
                    .hasKeyWithValue("responseObject.nic", String.valueOf(RegistrationCompleteLocal.get("nic")))
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void EKycRegistrationCompleteWithInvalidParamiters(String accessToken, String CusRegistrationCompleteLocalPath, String randomMobileNumber) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().EKycRegistrationComplete();
        String cID = dbConnector.getCustomerId(randomMobileNumber);
        endpoint = endpoint.replace("{cID}", cID);
        JSONObject RegistrationCompleteLocal = (JSONObject) utils.jsonReader(CusRegistrationCompleteLocalPath);
        RegistrationCompleteLocal.put("mobileNo", randomMobileNumber);
        RegistrationCompleteLocal.put("cID", cID);

        Response response = ReqresApi.postRequest(RegistrationCompleteLocal, endpoint, accessToken);
        try {
            System.out.println("EKyc Registration Complete Response:" + response.prettyPrint());
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "999")
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }
}
