package org.loit.api;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.loit.api.assertwrapper.ResponseAssert;
import org.loit.config.factory.ApiConfigFactory;
import org.loit.utils.Utility;

public class MobileReloadsRequest {

    Utility utils = new Utility();

    public void utilityPayment2FeesForMobileReloads(String loginAccessToken, String utilityPayment2feesPath) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().utilityPayment2fees();
        String deviceId = "bd6a67342b6c42f5f148b38f03667006";
        endpoint = endpoint.replace("{deviceId}", deviceId);

        JSONObject utilityPayment2feesPathReq = (JSONObject) utils.jsonReader(utilityPayment2feesPath).get("request");
        JSONObject utilityPayment2feesPathRes = (JSONObject) utils.jsonReader(utilityPayment2feesPath).get("responseObject");

        Response response = ReqresApi.postPaymentSources(utilityPayment2feesPathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
//                .hasKeyWithValue("responseObject" , String.valueOf(FundTransfer2feesPathRes))
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void utilityPayment2ForMobileReloads(String loginAccessToken, String utilityPayment2Path,String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().utilityPayment2();
        String deviceId = "bd6a67342b6c42f5f148b38f03667006";
        endpoint = endpoint.replace("{deviceId}", deviceId);

        JSONObject utilityPayment2PathReq = (JSONObject) utils.jsonReader(utilityPayment2Path).get("request");
        JSONObject utilityPayment2PathRes = (JSONObject) utils.jsonReader(utilityPayment2Path).get("response");

        Response response = ReqresApi.postPaymentSources(utilityPayment2PathReq, endpoint,loginAccessToken);
        System.out.println("response:" + response.prettyPrint());
        System.out.println("our response: "+utilityPayment2PathRes.get("responseObject"));
        Object responseObjectObject = utilityPayment2PathRes.get("responseObject");
        JSONObject responseObject = (JSONObject) responseObjectObject;
        Object message = responseObject.get("message");
        System.out.println("our response 2: "+utilityPayment2PathRes.get(message));
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
//                .hasKeyWithValue("responseObject."+ResponseMessage, String.valueOf(utilityPayment2PathRes.get(ResponseMessage)))
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }
}
