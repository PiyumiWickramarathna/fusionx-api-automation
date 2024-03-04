package org.loit.api;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.loit.config.factory.ApiConfigFactory;
import org.loit.utils.Utility;
import org.loit.api.assertwrapper.ResponseAssert;

public class PayBillsRequest {

    Utility utils = new Utility();

    String deviceID = "23fb0c6d6791eba11c41ce5327ba4305";

    public void postCompleteTransaction(String staticLoginAccessToken,String completeTransactionPath, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().postCompleteTransactionPath().replace("{deviceID}", deviceID);

        JSONObject registrationInItReq = (JSONObject) utils.jsonReader(completeTransactionPath).get("request");
        JSONObject registrationInItRes = (JSONObject) utils.jsonReader(completeTransactionPath).get("responseObject");

        Response response = ReqresApi.postRequest(registrationInItReq,endpoint, staticLoginAccessToken);
        System.out.println("Complete Transaction: " + response.asPrettyString());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .hasKeyWithValue("responseCode", "000")
                .hasKeyWithValue("responseObject." + ResponseMessage, String.valueOf(registrationInItRes.get(ResponseMessage)))
                .assertAll();
    }

    public void postCompleteTransactionWithInvalidAccessToken(String staticLoginAccessToken,String completeTransactionPath, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().postCompleteTransactionPath().replace("{deviceID}", deviceID);

        JSONObject registrationInItReq = (JSONObject) utils.jsonReader(completeTransactionPath).get("request");
        JSONObject registrationInItRes = (JSONObject) utils.jsonReader(completeTransactionPath).get("responseObject");

        Response response = ReqresApi.postRequest(registrationInItReq,endpoint, staticLoginAccessToken);
        System.out.println("Complete Transaction with Invalid Access Token: " + response.asPrettyString());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
//                .hasKeyWithValue("responseCode", "999")
//                .("responseObject." + ResponseMessage, String.valueOf(registrationInItRes.get(ResponseMessage)))
                .assertAll();
    }

    public void postCompleteTransactionWithInvalidDeviceID(String staticLoginAccessToken, String completeTransactionPath, String ResponseMessage) throws Exception {
        String invalidDeviceID = "5fec0aaf1fc65d8c4a6fe7b4fc943aae";
        String endpoint = ApiConfigFactory.getConfig().postCompleteTransactionPath().replace("{deviceID}", invalidDeviceID);

        JSONObject registrationInItReq = (JSONObject) utils.jsonReader(completeTransactionPath).get("request");
        JSONObject registrationInItRes = (JSONObject) utils.jsonReader(completeTransactionPath).get("responseObject");

        Response response = ReqresApi.postRequest(registrationInItReq, endpoint, staticLoginAccessToken);
        System.out.println("Complete Transaction with Invalid Device ID: " + response.asPrettyString());
        try {
            String expectedErrorMessage = (String) registrationInItRes.get("errorMessage");
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
//                .hasKeyWithValue("responseCode", "999")
//                .hasErrorMessage(expectedErrorMessage)
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void postCompleteTransactionWithExceedAmount(String staticLoginAccessToken,String completeTransactionPath, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().postCompleteTransactionPath().replace("{deviceID}", deviceID);

        JSONObject registrationInItReq = (JSONObject) utils.jsonReader(completeTransactionPath).get("request");
        JSONObject registrationInItRes = (JSONObject) utils.jsonReader(completeTransactionPath).get("responseObject");

        Response response = ReqresApi.postRequest(registrationInItReq,endpoint, staticLoginAccessToken);
        System.out.println("Complete Transaction with Exceed Amount: " + response.asPrettyString());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
//                .hasKeyWithValue("responseCode", "000")
//                .hasKeyWithValue("responseObject." + ResponseMessage, String.valueOf(registrationInItRes.get(ResponseMessage)))
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

}
