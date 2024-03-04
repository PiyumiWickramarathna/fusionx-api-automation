package org.loit.api;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.loit.api.assertwrapper.ResponseAssert;
import org.loit.utils.DatabaseConnector;
import org.loit.utils.Utility;
import org.loit.config.factory.ApiConfigFactory;

public class SendMoneyRequest {
    @Getter
    @Setter
    public String verifyOtp;
    String deviceID = "18979871eacfbe56b3e9bd5f43a8db64";
    Utility utils = new Utility();
    DatabaseConnector dbConnector = new DatabaseConnector();
    public void fundTransfer2feesForSendMoneyBankTransaction(String deviceId,String loginAccessToken, String fundTransfer2FeesPath) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().fund_transfer2fees();
        endpoint = endpoint.replace("{deviceId}", deviceId);

        JSONObject fundTransfer2FeesPathReq = (JSONObject) utils.jsonReader(fundTransfer2FeesPath).get("request");
        JSONObject fundTransfer2FeesPathRes = (JSONObject) utils.jsonReader(fundTransfer2FeesPath).get("response");

        Response response = ReqresApi.postPaymentSources(fundTransfer2FeesPathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void fundTransfer2ForSendMoneyBankTransaction(String deviceId,String loginAccessToken, String fundTransfer2Path) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().fund_transfer2();
        endpoint = endpoint.replace("{deviceId}", deviceId);

        JSONObject fundTransfer2PathReq = (JSONObject) utils.jsonReader(fundTransfer2Path).get("request");
        JSONObject fundTransfer2PathRes = (JSONObject) utils.jsonReader(fundTransfer2Path).get("response");

        Response response = ReqresApi.postPaymentSources(fundTransfer2PathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .hasKeyWithValue("responseObject.message", "Your transaction completed successfully.")
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void checkOtpEnableForAmountExceedLimit(String deviceId, String loginAccessToken, String checkOtpEnablePath) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().checkOtpEnable();
        endpoint = endpoint.replace("{deviceId}", deviceId);

        JSONObject checkOtpEnablePathReq = (JSONObject) utils.jsonReader(checkOtpEnablePath).get("request");
        JSONObject checkOtpEnablePathRes = (JSONObject) utils.jsonReader(checkOtpEnablePath).get("responseObject");

        Response response = ReqresApi.postPaymentSources(checkOtpEnablePathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
//                .hasKeyWithValue("responseObject" , String.valueOf(checkOtpEnablePathRes))
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }
    public void verifyOtpForConstant(String deviceId, String loginAccessToken, String verifyOtpPath) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().verifyOtp();
        endpoint = endpoint.replace("{deviceId}", deviceId);
        verifyOtp = dbConnector.verifyOtpForFundTransactions();

        JSONObject verifyOtpPathReq = (JSONObject) utils.jsonReader(verifyOtpPath).get("request");
        verifyOtpPathReq.put("otp",verifyOtp);


        Response response = ReqresApi.postPaymentSources(verifyOtpPathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
//                .hasKeyWithValue("responseObject" , String.valueOf(checkOtpEnablePathRes))
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void verifyOtpForAmountExceedLimit(String deviceId, String loginAccessToken, String verifyOtpPath) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().verifyOtp();
        endpoint = endpoint.replace("{deviceId}", deviceId);
        String verifyOtp = dbConnector.verifyOtpForFundTransactions();

        JSONObject verifyOtpPathReq = (JSONObject) utils.jsonReader(verifyOtpPath).get("request");
        JSONObject verifyOtpPathRes = (JSONObject) utils.jsonReader(verifyOtpPath).get("responseObject");

        verifyOtpPathReq.put("otp",verifyOtp);

        Response response = ReqresApi.postPaymentSources(verifyOtpPathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
//                .hasKeyWithValue("responseObject" , String.valueOf(checkOtpEnablePathRes))
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void postCompleteMobileTransaction(String staticLoginAccessToken, String completeTransactionPath, String responseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().postCompleteMobileTransactionPath().replace("{deviceID}", deviceID);

        JSONObject registrationInItReq = (JSONObject) utils.jsonReader(completeTransactionPath).get("request");
        JSONObject registrationInItRes = (JSONObject) utils.jsonReader(completeTransactionPath).get("responseObject");

        Response response = ReqresApi.postRequest(registrationInItReq,endpoint, staticLoginAccessToken);
        System.out.println("Complete Transaction: " + response.asPrettyString());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
//                .hasKeyWithValue("responseCode", "000")
//                .hasKeyWithValue("responseObject." + responseMessage, String.valueOf(registrationInItRes.get(responseMessage)))
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void postCompleteMobileTransactionWithExceedAmount(String staticLoginAccessToken, String completeTransactionPath, String responseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().postCompleteMobileTransactionPath().replace("{deviceID}", deviceID);

        JSONObject registrationInItReq = (JSONObject) utils.jsonReader(completeTransactionPath).get("request");
        JSONObject registrationInItRes = (JSONObject) utils.jsonReader(completeTransactionPath).get("responseObject");

        Response response = ReqresApi.postRequest(registrationInItReq,endpoint, staticLoginAccessToken);
        System.out.println("Complete Transaction with Exceed Amount: " + response.asPrettyString());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
//                .hasKeyWithValue("responseCode", "000")
//                .hasKeyWithValue("responseObject." + responseMessage, String.valueOf(registrationInItRes.get(responseMessage)))
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void postCompleteMobileTransactionWithInvalidAccessToken(String staticLoginAccessToken, String completeTransactionPath, String responseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().postCompleteMobileTransactionPath();
        endpoint = endpoint.replace("{deviceID}",deviceID);

        JSONObject registrationInItReq = (JSONObject) utils.jsonReader(completeTransactionPath).get("request");
        JSONObject registrationInItRes = (JSONObject) utils.jsonReader(completeTransactionPath).get("responseObject");

        Response response = ReqresApi.postRequest(registrationInItReq,endpoint, staticLoginAccessToken);
        System.out.println("Complete Transaction with Invalid Access Token: " + response.asPrettyString());
        String expectedErrorMessage = (String) registrationInItRes.get("errorMessage");
        try {
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

    public void postCompleteMobileTransactionWithInvalidDeviceID(String staticLoginAccessToken, String completeTransactionPath, String responseMessage) throws Exception {
        String invalidDeviceID = "18979871eacfbe56b3e9bd5f43a8db64";
        String endpoint = ApiConfigFactory.getConfig().postCompleteMobileTransactionPath().replace("{deviceID}", invalidDeviceID);

        JSONObject registrationInItReq = (JSONObject) utils.jsonReader(completeTransactionPath).get("request");
        JSONObject registrationInItRes = (JSONObject) utils.jsonReader(completeTransactionPath).get("responseObject");

        Response response = ReqresApi.postRequest(registrationInItReq, endpoint, staticLoginAccessToken);
        System.out.println("Complete Transaction with Invalid Device ID: " + response.asPrettyString());

        String expectedErrorMessage = (String) registrationInItRes.get("errorMessage");
        try {
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
    public void makePayment2feesForSendMoneyMobileTransactionForIPayCustomers(String deviceId,String loginAccessToken, String makePayment2FeesPath) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().makePayment2fees();
        endpoint = endpoint.replace("{deviceId}", deviceId);

        JSONObject makePayment2FeesPathReq = (JSONObject) utils.jsonReader(makePayment2FeesPath).get("request");
        JSONObject makePayment2FeesPathRes = (JSONObject) utils.jsonReader(makePayment2FeesPath).get("response");

        Response response = ReqresApi.postPaymentSources(makePayment2FeesPathReq,endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }
    public void makePayment2ForSendMoneyMobileTransactionForIPayCustomers(String deviceId,String loginAccessToken, String makePayment2Path) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().makePayment2();
        endpoint = endpoint.replace("{deviceId}", deviceId);

        JSONObject makePayment2PathReq = (JSONObject) utils.jsonReader(makePayment2Path).get("request");
        JSONObject makePayment2PathRes = (JSONObject) utils.jsonReader(makePayment2Path).get("response");

        Response response = ReqresApi.postPaymentSources(makePayment2PathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }
    public void sendAnyone2feesForSendMoneyMobileTransactionForNonIPayCustomers(String deviceId,String loginAccessToken, String sendAnyone2FeesPath) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().sendAnyone2fees();
        endpoint = endpoint.replace("{deviceId}", deviceId);

        JSONObject sendAnyone2FeesPathReq = (JSONObject) utils.jsonReader(sendAnyone2FeesPath).get("request");
        JSONObject sendAnyone2FeesPathRes = (JSONObject) utils.jsonReader(sendAnyone2FeesPath).get("response");

        Response response = ReqresApi.postPaymentSources(sendAnyone2FeesPathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void sendAnyone2ForSendMoneyMobileTransactionForNonIPayCustomers(String deviceId,String loginAccessToken, String sendAnyone2Path) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().sendAnyone2fees();
        endpoint = endpoint.replace("{deviceId}", deviceId);

        JSONObject sendAnyone2PathReq = (JSONObject) utils.jsonReader(sendAnyone2Path).get("request");
        JSONObject sendAnyone2PathRes = (JSONObject) utils.jsonReader(sendAnyone2Path).get("response");

        Response response = ReqresApi.postPaymentSources(sendAnyone2PathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }
}
