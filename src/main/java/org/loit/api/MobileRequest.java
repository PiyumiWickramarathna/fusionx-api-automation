package org.loit.api;

import io.restassured.response.Response;
import org.loit.api.assertwrapper.ResponseAssert;
import org.loit.config.factory.ApiConfigFactory;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.loit.utils.DatabaseConnector;
import org.loit.utils.Utility;

import java.util.Base64;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;


public class MobileRequest {
    @Getter
    @Setter
    public String otp;

    @Getter
    @Setter
    public static String randomMobileNumber;

    Utility utils = new Utility();
    DatabaseConnector dbConnector = new DatabaseConnector();

    public void postCusRegInIt(String accessToken, String registrationInItPath, String randomMobileNumber, String ResponseMessage) throws IOException, ParseException {
        String endpoint = ApiConfigFactory.getConfig().postUserEndpoint();


        JSONObject registrationInItReq = (JSONObject) utils.jsonReader(registrationInItPath).get("request");
        JSONObject registrationInItRes = (JSONObject) utils.jsonReader(registrationInItPath).get("responseObject");
        registrationInItReq.put("mobileNo", randomMobileNumber);


        Response response = org.loit.api.ReqresApi.postRequest(registrationInItReq, endpoint, accessToken);
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .hasKeyWithValue("responseObject." + ResponseMessage, String.valueOf(registrationInItRes.get(ResponseMessage)))
                .assertAll();
    }


    public void verifyOtpRequest(String accessToken, String verifyOtpForRegistrationPath, String randomMobileNumber, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().postVerifyOtp();
        otp = dbConnector.getRegistrationOtp(randomMobileNumber);

        JSONObject verifyOtpForRegistrationReq = (JSONObject) utils.jsonReader(verifyOtpForRegistrationPath).get("request");
        JSONObject verifyOtpForRegistrationRes = (JSONObject) utils.jsonReader(verifyOtpForRegistrationPath).get("responseObject");

        verifyOtpForRegistrationReq.put("mobileNo", randomMobileNumber);
        verifyOtpForRegistrationReq.put("otp", otp);


        Response response = org.loit.api.ReqresApi.postRequest(verifyOtpForRegistrationReq, endpoint, accessToken);
        System.out.println("response otp" + response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .hasKeyWithValue("responseObject." + ResponseMessage, String.valueOf(verifyOtpForRegistrationRes.get(ResponseMessage)))
                .assertAll();
    }

    public void verifyOtpRequestReSend(String accessToken, String verifyOtpForRegistrationPath, String randomMobileNumber, String ResponseMessageIncorrectOtp) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().postVerifyOtp();

        JSONObject verifyOtpForRegistrationReq = (JSONObject) utils.jsonReader(verifyOtpForRegistrationPath).get("request");
        JSONObject verifyOtpForRegistrationRes = (JSONObject) utils.jsonReader(verifyOtpForRegistrationPath).get("responseObject");

        verifyOtpForRegistrationReq.put("mobileNo", randomMobileNumber);
        verifyOtpForRegistrationReq.put("otp", otp);

        Response response = org.loit.api.ReqresApi.postRequest(verifyOtpForRegistrationReq, endpoint, accessToken);
        System.out.println("response otp" + response.prettyPrint());
        System.out.println("response for vOTP" + verifyOtpForRegistrationRes);
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .hasKeyWithValue("responseObject." + ResponseMessageIncorrectOtp, String.valueOf(verifyOtpForRegistrationRes.get(ResponseMessageIncorrectOtp)))
                .assertAll();
    }

    public void postCusRegistration(String accessToken, String CusRegistrationCompleteLocalPath, String randomMobileNumber, String ResponseMessage) throws IOException, ParseException {

        String endpoint = ApiConfigFactory.getConfig().postCustomerRegistration();
        JSONObject RegistrationCompleteLocal = (JSONObject) utils.jsonReader(CusRegistrationCompleteLocalPath);
        RegistrationCompleteLocal.put("mobileNo", randomMobileNumber);

        Response response = org.loit.api.ReqresApi.postRequest(RegistrationCompleteLocal, endpoint, accessToken);
        System.out.println("response : registration complete" + response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .hasKeyWithValue("responseObject.phone", String.valueOf(RegistrationCompleteLocal.get("mobileNo")))
                .hasKeyWithValue("responseObject.nic", String.valueOf(RegistrationCompleteLocal.get("nic")))
                .assertAll();
    }

    public void postCusRegistrationNegative(String accessToken, String CusRegistrationCompleteLocalPath, String randomMobileNumber, String ResponseMessageForInCompleteReg) throws IOException, ParseException {

        String endpoint = ApiConfigFactory.getConfig().postCustomerRegistration();
        JSONObject RegistrationCompleteLocalReq = (JSONObject) utils.jsonReader(CusRegistrationCompleteLocalPath).get("request");
        JSONObject RegistrationCompleteLocalRes = (JSONObject) utils.jsonReader(CusRegistrationCompleteLocalPath).get("responseObject");
        RegistrationCompleteLocalReq.put("mobileNo", randomMobileNumber);

        Response response = org.loit.api.ReqresApi.postRequest(RegistrationCompleteLocalReq, endpoint, accessToken);
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .hasKeyWithValue("responseObject." + ResponseMessageForInCompleteReg, String.valueOf(RegistrationCompleteLocalRes.get(ResponseMessageForInCompleteReg)))
                .assertAll();
    }


    public void GetKYCDocumentTypeFor_Y(String loginAccessToken, String randomMobileNumber, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().kycDocumentType_Y();
        String cID = dbConnector.getCustomerId(randomMobileNumber);
        endpoint = endpoint.replace("{cID}", cID);
        System.out.println("New Customer ID: " + cID);
        Response response = org.loit.api.ReqresApi.geKycTypes(endpoint, loginAccessToken);
        System.out.println("kyc Document Type: " + response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .assertAll();
    }


    public void GetKYCDocumentTypeFor_N(String loginAccessToken, String randomMobileNumber, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().kycDocumentType_N();
        String cID = dbConnector.getCustomerId(randomMobileNumber);
        endpoint = endpoint.replace("{cID}", cID);
        Response response = org.loit.api.ReqresApi.getRequestLogIn(endpoint, loginAccessToken);
        System.out.println("Response in Negaive: " + response.prettyPrint());

        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                //.hasKeyWithValue("responseObject."+ResponseMessage, String.valueOf(KYCDocumentType_N.get(ResponseMessage)))
                .assertAll();
    }

    public void PostCusEKycFaceCompare_Negative(String cusLoginAccess, String CusEKycFaceComparePath, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().Customer_EKyc_FaceCompare();

        JSONObject CusEKycFaceCompareReq = (JSONObject) utils.jsonReader(CusEKycFaceComparePath).get("request");
        JSONObject CusEKycFaceCompareRes = (JSONObject) utils.jsonReader(CusEKycFaceComparePath).get("responseObject");

        Response response = org.loit.api.ReqresApi.postRequest(CusEKycFaceCompareReq, endpoint, cusLoginAccess);
        System.out.println("response:" + response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .hasKeyWithValue("responseObject." + ResponseMessage, String.valueOf(CusEKycFaceCompareRes.get(ResponseMessage)))
                .assertAll();
    }

    public void PostCusEKycOcr_Negative(String cusLoginAccess, String CusEKycOcrPath, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().Customer_EKyc_Ocr();

        JSONObject CusEKycOcrReq = (JSONObject) utils.jsonReader(CusEKycOcrPath).get("request");
        JSONObject CusEKycOcrRes = (JSONObject) utils.jsonReader(CusEKycOcrPath).get("responseObject");

        Response response = org.loit.api.ReqresApi.postRequest(CusEKycOcrReq, endpoint, cusLoginAccess);
        System.out.println("response:" + response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .hasKeyWithValue("responseObject." + ResponseMessage, String.valueOf(CusEKycOcrRes.get(ResponseMessage)))
                .assertAll();
    }

    public void PostCusEKycOcr(String cusLoginAccess, String CusEKycOcrPath, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().Customer_EKyc_Ocr();

        JSONObject CusEKycOcrReq = (JSONObject) utils.jsonReader(CusEKycOcrPath).get("request");
        byte[] imageBytes = Files.readAllBytes(Paths.get("src/test/resources/Images/NICImage1.jpg"));
        String NICImage = Base64.getEncoder().encodeToString(imageBytes);
        CusEKycOcrReq.put("idImage", NICImage);

        JSONObject CusEKycOcrRes = (JSONObject) utils.jsonReader(CusEKycOcrPath).get("responseObject");

        Response response = org.loit.api.ReqresApi.postRequest(CusEKycOcrReq, endpoint, cusLoginAccess);
        System.out.println("response:" + response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
//                .hasKeyWithValue("responseObject."+ResponseMessage, String.valueOf(CusEKycOcrRes.get(ResponseMessage)))
                .assertAll();
    }

    public void loginByMobileNo(String accessToken, String randomMobileNumber) throws IOException, ParseException {
        String endpoint = ApiConfigFactory.getConfig().CustomerLoginByMobileNo().replace("{{randomMobileNumber}}", randomMobileNumber);
//        JSONObject CusLoginByMobileNoReq = (JSONObject) utils.jsonReader(CusLoginByMobileNo).get("request");
//        JSONObject CusLoginByMobileNoRes = (JSONObject) utils.jsonReader(CusLoginByMobileNo).get("responseObject");
//        CusLoginByMobileNoReq.put("mobileNo",randomMobileNumber);

        Response response = org.loit.api.ReqresApi.getRequest(endpoint, accessToken, randomMobileNumber);
        System.out.println("getByMobile Response:" + response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
//                .hasKeyWithValue("responseObject."+ResponseMessage, String.valueOf(CusLoginByMobileNoRes.get(ResponseMessage)))
                .assertAll();
    }

    public void eKycRegistrationComplete(String accessToken, String CusRegistrationCompleteLocalPath, String randomMobileNumber) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().EKycRegistrationComplete();
        String cID = dbConnector.getCustomerId(randomMobileNumber);
        endpoint = endpoint.replace("{cID}", cID);
        JSONObject RegistrationCompleteLocal = (JSONObject) utils.jsonReader(CusRegistrationCompleteLocalPath);
        RegistrationCompleteLocal.put("mobileNo", randomMobileNumber);
        RegistrationCompleteLocal.put("cID", cID);

        Response response = org.loit.api.ReqresApi.postRequest(RegistrationCompleteLocal, endpoint, accessToken);
        System.out.println("EKyc Registration Complete Response:" + response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .hasKeyWithValue("responseObject.phone", String.valueOf(RegistrationCompleteLocal.get("mobileNo")))
                .hasKeyWithValue("responseObject.nic", String.valueOf(RegistrationCompleteLocal.get("nic")))
                .assertAll();
    }

    public void payment_source_NForBankTransaction(String loginAccessToken) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().payment_source_N();
        String Customer_ID = "5333";
        endpoint = endpoint.replace("{Customer_ID}", Customer_ID);
        Response response = org.loit.api.ReqresApi.getPaymentSources(endpoint,loginAccessToken);
        System.out.println(response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .assertAll();
    }

    public void bank_actionForBankTransaction(String loginAccessToken) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().bank_action();
        Response response = org.loit.api.ReqresApi.getPaymentSources(endpoint,loginAccessToken);
        System.out.println(response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .assertAll();
    }

    public void locationCapturingForBankTransaction(String loginAccessToken) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().Location_Capturing();
        String Customer_ID = "5333";
        endpoint = endpoint.replace("{Customer_ID}", Customer_ID);
        Response response = org.loit.api.ReqresApi.getPaymentSources(endpoint,loginAccessToken);
        System.out.println(response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .assertAll();
    }

    public void availableTransLimitForBankTransaction(String loginAccessToken, String AvailableTransLimitPath) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().AvailableTransLimit();
        String Customer_ID = "5333";
        endpoint = endpoint.replace("{Customer_ID}", Customer_ID);

        JSONObject AvailableTransLimitPathReq = (JSONObject) utils.jsonReader(AvailableTransLimitPath).get("request");
        JSONObject AvailableTransLimitPathRes = (JSONObject) utils.jsonReader(AvailableTransLimitPath).get("responseObject");

        Response response = org.loit.api.ReqresApi.postPaymentSources(AvailableTransLimitPathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
//                .hasKeyWithValue("responseObject" , String.valueOf(AvailableTransLimitPathRes))
                .assertAll();
    }
    public void payment_source_YForBankTransaction(String loginAccessToken) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().payment_source_Y();
        String Customer_ID = "5333";
        endpoint = endpoint.replace("{Customer_ID}", Customer_ID);
        Response response = org.loit.api.ReqresApi.getPaymentSources(endpoint,loginAccessToken);

        System.out.println(response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .assertAll();
    }

    public void fundTransferForBankTransaction(String loginAccessToken, String FundTransferPath) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().fund_transfer();
        String deviceId = "5fec0aaf1fc65d8c4a6fe7b4fc943aae";
        endpoint = endpoint.replace("{deviceId}", deviceId);

        JSONObject FundTransferPathReq = (JSONObject) utils.jsonReader(FundTransferPath).get("request");
        JSONObject FundTransferPathRes = (JSONObject) utils.jsonReader(FundTransferPath).get("responseObject");

        Response response = org.loit.api.ReqresApi.postPaymentSources(FundTransferPathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
//                .hasKeyWithValue("responseObject" , String.valueOf(FundTransferPathRes))
                .assertAll();
    }

    public void fundTransfer2FeesForBankTransaction(String loginAccessToken, String FundTransfer2feesPath) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().fund_transfer2fees();
        String deviceId = "5fec0aaf1fc65d8c4a6fe7b4fc943aae";
        endpoint = endpoint.replace("{deviceId}", deviceId);

        JSONObject FundTransfer2feesPathReq = (JSONObject) utils.jsonReader(FundTransfer2feesPath).get("request");
        JSONObject FundTransfer2feesPathRes = (JSONObject) utils.jsonReader(FundTransfer2feesPath).get("responseObject");

        Response response = org.loit.api.ReqresApi.postPaymentSources(FundTransfer2feesPathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
//                .hasKeyWithValue("responseObject" , String.valueOf(FundTransfer2feesPathRes))
                .assertAll();
    }

    public void checkOtpEnableForBankTransaction(String loginAccessToken, String FundTransfer2Path) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().checkOtpEnable();
        String deviceId = "5fec0aaf1fc65d8c4a6fe7b4fc943aae";
        endpoint = endpoint.replace("{deviceId}", deviceId);

        JSONObject FundTransfer2PathReq = (JSONObject) utils.jsonReader(FundTransfer2Path).get("request");
        JSONObject FundTransfer2PathRes = (JSONObject) utils.jsonReader(FundTransfer2Path).get("responseObject");

        Response response = org.loit.api.ReqresApi.postPaymentSources(FundTransfer2PathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
//                .hasKeyWithValue("responseObject" , String.valueOf(FundTransfer2PathRes))
                .assertAll();
    }

    public void fundTransfer2ForBankTransaction(String loginAccessToken, String FundTransfer2feesPath) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().fund_transfer2();
        String deviceId = "5fec0aaf1fc65d8c4a6fe7b4fc943aae";
        endpoint = endpoint.replace("{deviceId}", deviceId);

        JSONObject FundTransfer2feesPathReq = (JSONObject) utils.jsonReader(FundTransfer2feesPath).get("request");
        JSONObject FundTransfer2feesPathRes = (JSONObject) utils.jsonReader(FundTransfer2feesPath).get("responseObject");

        Response response = org.loit.api.ReqresApi.postPaymentSources(FundTransfer2feesPathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
//                .hasKeyWithValue("responseObject" , String.valueOf(FundTransfer2feesPathRes))
                .assertAll();
    }

}