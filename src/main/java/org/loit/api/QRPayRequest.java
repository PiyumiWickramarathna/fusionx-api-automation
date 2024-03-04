package org.loit.api;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.loit.api.assertwrapper.ResponseAssert;
import org.loit.config.factory.ApiConfigFactory;
import org.loit.utils.DatabaseConnector;
import org.loit.utils.Utility;

public class QRPayRequest {
    @Getter
    @Setter
    public String verifyOtp;

    Utility utils = new Utility();
    DatabaseConnector dbConnector = new DatabaseConnector();

    public void getQRInstrumentRefNo(String instrumentRefNo,String loginAccessToken) throws Exception{
        String endpoint = ApiConfigFactory.getConfig().QRInstrumentRefNo();
        endpoint = endpoint.replace("{instrumentRefNo}", instrumentRefNo);
        Response response = ReqresApi.getPaymentSources(endpoint,loginAccessToken);
        System.out.println(response.prettyPrint());
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
    public void instrumentPay2Fees(String deviceId,String loginAccessToken, String instrumentPay2FeesPath) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().instrumentPay2fees();
        endpoint = endpoint.replace("{deviceId}", deviceId);

        JSONObject instrumentPay2FeesPathReq = (JSONObject) utils.jsonReader(instrumentPay2FeesPath).get("request");
        JSONObject instrumentPay2FeesPathRes = (JSONObject) utils.jsonReader(instrumentPay2FeesPath).get("response");

        Response response = ReqresApi.postPaymentSources(instrumentPay2FeesPathReq, endpoint,loginAccessToken);
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

    public void instrumentPay2(String deviceId,String loginAccessToken, String instrumentPay2Path) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().instrumentPay2();
        endpoint = endpoint.replace("{deviceId}", deviceId);

        JSONObject instrumentPay2PathReq = (JSONObject) utils.jsonReader(instrumentPay2Path).get("request");
        JSONObject instrumentPay2PathRes = (JSONObject) utils.jsonReader(instrumentPay2Path).get("response");

        Response response = ReqresApi.postPaymentSources(instrumentPay2PathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .hasKeyWithValue("responseObject.message", "Your Transaction Completed Successfully")
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

        JSONObject verifyOtpPathReq = (JSONObject) utils.jsonReader(verifyOtpPath).get("request");

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
        try {
            System.out.println("response:" + response.prettyPrint());
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
//                .hasKeyWithValue("responseObject" , String.valueOf(checkOtpEnablePathRes))
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

}
