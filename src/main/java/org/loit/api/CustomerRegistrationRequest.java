package org.loit.api;

import io.restassured.response.Response;
import org.loit.api.assertwrapper.ResponseAssert;
import org.loit.config.factory.ApiConfigFactory;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.loit.utils.DatabaseConnector;
import org.loit.utils.Utility;


public class CustomerRegistrationRequest {
    @Getter
    @Setter
    public String otp;

    @Getter
    @Setter
    public static String randomMobileNumber;

    Utility utils = new Utility();

    DatabaseConnector dbConnector = new DatabaseConnector();

    public void cusRegInIt(String accessToken, String registrationInItPath, String randomMobileNumber, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().postCusRegInIt();
        System.out.println("registration init "+registrationInItPath);

        JSONObject registrationInItReq = (JSONObject) utils.jsonReader(registrationInItPath).get("request");
        JSONObject registrationInItRes = (JSONObject) utils.jsonReader(registrationInItPath).get("responseObject");
        registrationInItReq.put("mobileNo", randomMobileNumber);


        Response response = org.loit.api.ReqresApi.postRequest(registrationInItReq, endpoint, accessToken);
        System.out.println(response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseObject." + ResponseMessage, String.valueOf(registrationInItRes.get(ResponseMessage)))
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }


    public void verifyOtpRequest(String accessToken, String verifyOtpForRegistrationPath, String randomMobileNumber, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().postVerifyOtp();
        otp = dbConnector.getRegistrationOtp(randomMobileNumber);

        JSONObject verifyOtpForRegistrationReq = (JSONObject) utils.jsonReader(verifyOtpForRegistrationPath).get("request");
        JSONObject verifyOtpForRegistrationRes = (JSONObject) utils.jsonReader(verifyOtpForRegistrationPath).get("responseObject");

        verifyOtpForRegistrationReq.put("mobileNo", randomMobileNumber);
        verifyOtpForRegistrationReq.put("otp", otp);


        Response response = org.loit.api.ReqresApi.postRequest(verifyOtpForRegistrationReq, endpoint, accessToken);
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseObject." + ResponseMessage, String.valueOf(verifyOtpForRegistrationRes.get(ResponseMessage)))
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void verifyOtpRequestReSend(String accessToken, String verifyOtpForRegistrationPath, String randomMobileNumber, String ResponseMessageIncorrectOtp) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().postVerifyOtp();

        JSONObject verifyOtpForRegistrationReq = (JSONObject) utils.jsonReader(verifyOtpForRegistrationPath).get("request");
        JSONObject verifyOtpForRegistrationRes = (JSONObject) utils.jsonReader(verifyOtpForRegistrationPath).get("responseObject");

        verifyOtpForRegistrationReq.put("mobileNo", randomMobileNumber);
        verifyOtpForRegistrationReq.put("otp", otp);

        Response response = org.loit.api.ReqresApi.postRequest(verifyOtpForRegistrationReq, endpoint, accessToken);
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseObject." + ResponseMessageIncorrectOtp, String.valueOf(verifyOtpForRegistrationRes.get(ResponseMessageIncorrectOtp)))
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void cusRegComplete(String accessToken, String CusRegistrationCompleteLocalPath, String randomMobileNumber, String ResponseMessage) throws Exception {

        String endpoint = ApiConfigFactory.getConfig().postCustomerRegistration();
        JSONObject RegistrationCompleteLocal = (JSONObject) utils.jsonReader(CusRegistrationCompleteLocalPath);
        RegistrationCompleteLocal.put("mobileNo", randomMobileNumber);

        Response response = org.loit.api.ReqresApi.postRequest(RegistrationCompleteLocal, endpoint, accessToken);
        System.out.println("response : registration complete" + response.prettyPrint());
        try{
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .hasKeyWithValue("responseObject.phone", String.valueOf(RegistrationCompleteLocal.get("mobileNo")))
                .hasKeyWithValue("responseObject.nic", String.valueOf(RegistrationCompleteLocal.get("nic")))
                .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }

    }

    public void cusRegCompleteNegative(String accessToken, String CusRegistrationCompleteLocalPath, String randomMobileNumber, String ResponseMessageForInCompleteReg) throws Exception {

        String endpoint = ApiConfigFactory.getConfig().postCustomerRegistration();
        JSONObject RegistrationCompleteLocalReq = (JSONObject) utils.jsonReader(CusRegistrationCompleteLocalPath).get("request");
        JSONObject RegistrationCompleteLocalRes = (JSONObject) utils.jsonReader(CusRegistrationCompleteLocalPath).get("responseObject");
        RegistrationCompleteLocalReq.put("mobileNo", randomMobileNumber);

        Response response = org.loit.api.ReqresApi.postRequest(RegistrationCompleteLocalReq, endpoint, accessToken);
        try{
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .hasKeyWithValue("responseObject." + ResponseMessageForInCompleteReg, String.valueOf(RegistrationCompleteLocalRes.get(ResponseMessageForInCompleteReg)))
                .assertAll();
        }
        catch(Exception e) {
            System.out.println("Response: ="+response.prettyPrint());
            throw new Exception(response.prettyPrint());
    }
}
}