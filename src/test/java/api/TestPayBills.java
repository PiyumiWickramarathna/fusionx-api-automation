package api;

import lombok.Getter;
import lombok.Setter;
import org.loit.api.PayBillsRequest;
import org.loit.utils.AccessTokenGenerator;
import org.loit.utils.StaticUserLoginAccessToken;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestListenerApi;

@Listeners(TestListenerApi.class)
public class TestPayBills {
    PayBillsRequest payBills = new PayBillsRequest();

    @Getter
    @Setter
    public static String staticLoginAccessToken;

    @Getter @Setter
    public static String accessToken;


    @BeforeClass(groups = {"regression", "paybills"})
    public static void setUp() throws Exception {
        staticLoginAccessToken = StaticUserLoginAccessToken.StaticCustomerLoginAccessTokenCreation();

        AccessTokenGenerator accessTokenCreation = new AccessTokenGenerator();
        accessToken = accessTokenCreation.getAccessTokenCreation();
        System.out.println("Static Login Access Token: " + staticLoginAccessToken + "\n" + "Login Access Token: " + accessToken);
    }

    @Test(priority = 1, testName = "Verify system behavior when pay bills with valid parameters.", groups = {"regression", "paybills"})
    public void PayBills() throws Exception {
        String ResponseMessage = "message";
        String completeTransactionPath = "src/test/resources/requests/payBills/complete-transaction.json";
        payBills.postCompleteTransaction(staticLoginAccessToken,completeTransactionPath, ResponseMessage);
    }

    @Test(priority = 2, testName = "Verify system behavior when pay bills with invalid Access Token.", groups = {"regression", "paybills"})
    public void PayBillsInvalidAccessToken() throws Exception { // This test case is failing because of the bug in the API
        String ResponseMessage = "message";
        String completeTransactionPath = "src/test/resources/requests/payBills/complete-transaction-with-invalid-access-token.json";
        payBills.postCompleteTransactionWithInvalidAccessToken(accessToken,completeTransactionPath, ResponseMessage);
    }

    @Test(priority = 3, testName = "Verify system behavior when pay bills with invalid device ID.", groups = {"regression", "paybills"})
    public void PayBillsInvalidDeviceID() throws Exception {
        String ResponseMessage = "message";
        String completeTransactionPath = "src/test/resources/requests/payBills/complete-transaction-with-invalid-device-id.json";
        payBills.postCompleteTransactionWithInvalidDeviceID(staticLoginAccessToken,completeTransactionPath, ResponseMessage);
    }

    @Test(priority = 4, testName = "Verify system behavior when pay bills with Exceed Amount.", groups = {"regression", "paybills"})
    public void PayBillsExceedAmount() throws Exception {
        String ResponseMessage = "message";
        String completeTransactionExceedPath = "src/test/resources/requests/payBills/complete-transaction-with-exceed-amount.json";
        payBills.postCompleteTransactionWithExceedAmount(staticLoginAccessToken,completeTransactionExceedPath, ResponseMessage);
    }

}
