package api;

import lombok.Getter;
import lombok.Setter;
import org.loit.utils.LoginAccessToken;
import org.json.simple.parser.ParseException;
import org.loit.api.MobileReloadsRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestListenerApi;

import java.io.IOException;
@Listeners(TestListenerApi.class)
public class TestMobileReloads {

    MobileReloadsRequest mobileReloadRequest = new MobileReloadsRequest();


    @Getter
    @Setter
    public static String loginAccessToken;

    @BeforeClass (groups = {"regression", "mobilereloads"})
    public static void accessToken() throws IOException, ParseException {
        LoginAccessToken loginAccessTokenCreation = new LoginAccessToken();
        loginAccessToken = loginAccessTokenCreation.LoginAccessTokenCreation();
    }

    @Test(testName = "Verify system behavior when mobile reloads for voice and sms for Mobitel Rs.50 package", groups = {"regression", "mobilereloads"})
    public void mobileReloadsVoiceAndSmsMobitelV50() throws Exception{
        String ResponseMessage = "message";
        String utilityPayment2Path = "src/test/resources/requests/customer-mobileReloads/utilityPayment2-MobitelV50.json";
        String utilityPayment2feesPath = "src/test/resources/requests/customer-mobileReloads/utilityPayment2fees_MobitelV50.json";
        mobileReloadRequest.utilityPayment2FeesForMobileReloads(loginAccessToken,utilityPayment2feesPath);
        mobileReloadRequest.utilityPayment2ForMobileReloads(loginAccessToken,utilityPayment2Path,ResponseMessage);
    }

    @Test(testName = "Verify system behavior when mobile reloads for Data for Dialog D29 package" , groups = {"regression", "mobilereloads"})
    public void mobileReloadsDataDialogD29() throws Exception{
        String ResponseMessage = "message";
        String utilityPayment2Path = "src/test/resources/requests/customer-mobileReloads/utilityPayment2-DialogD29.json";
        String utilityPayment2feesPath = "src/test/resources/requests/customer-mobileReloads/utilityPayment2fees_DialogD29.json";
        mobileReloadRequest.utilityPayment2FeesForMobileReloads(loginAccessToken,utilityPayment2feesPath);
        mobileReloadRequest.utilityPayment2ForMobileReloads(loginAccessToken,utilityPayment2Path,ResponseMessage);
    }

    @Test(testName = "Verify system behavior when mobile reloads for Data for Hutch custom package" , groups = {"regression", "mobilereloads"})
    public void mobileReloadsDataHutchCustom() throws Exception{
        String ResponseMessage = "message";
        String utilityPayment2Path = "src/test/resources/requests/customer-mobileReloads/utilityPayment2-Hutch_Custom.json";
        String utilityPayment2feesPath = "src/test/resources/requests/customer-mobileReloads/utilityPayment2fees_Hutch_Custom.json";
        mobileReloadRequest.utilityPayment2FeesForMobileReloads(loginAccessToken,utilityPayment2feesPath);
        mobileReloadRequest.utilityPayment2ForMobileReloads(loginAccessToken,utilityPayment2Path,ResponseMessage);
    }

}
