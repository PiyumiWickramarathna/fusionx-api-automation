package api;

import lombok.Getter;
import lombok.Setter;
import org.loit.api.TransactionHistoryRequest;
import org.loit.utils.AccessTokenGenerator;
import org.loit.utils.StaticUserLoginAccessToken;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestListenerApi;

@Listeners(TestListenerApi.class)
public class TestTransactionHistory {

    TransactionHistoryRequest transactionHistory = new TransactionHistoryRequest();

    @Getter
    @Setter
    public static String staticLoginAccessToken;

    @Getter @Setter
    public static String accessToken;

    @BeforeClass (groups = {"regression", "transactionhistory"})
    public static void setUp() throws Exception {
        staticLoginAccessToken = StaticUserLoginAccessToken.StaticCustomerLoginAccessTokenCreation();

        AccessTokenGenerator accessTokenCreation = new AccessTokenGenerator();
        accessToken = accessTokenCreation.getAccessTokenCreation();
        System.out.println("Static Login Access Token: " + staticLoginAccessToken + "\n" + "Login Access Token: " + accessToken);
    }

    @Test(priority = 1, testName = "Verify system behavior when accessing transaction history with valid parameters.", groups = {"regression", "transactionhistory"})
    public void GetAllTransactionHistory() throws Exception {
        String CustomerID = "1020";
        String ResponseMessage = "message";
        transactionHistory.getTransactionHistory(CustomerID,staticLoginAccessToken, ResponseMessage);
    }

    @Test(priority = 2, testName = "Verify system behavior when accessing received transaction history with valid parameters.", groups = {"regression", "transactionhistory"})
    public void GetReceivedTransactionHistory() throws Exception {
        String CustomerID = "1020";
        String ResponseMessage = "message";
        transactionHistory.getReceivedTransactionHistory(CustomerID,staticLoginAccessToken, ResponseMessage);
    }

    @Test(priority = 3, testName = "Verify system behavior when accessing paid transaction history with valid parameters.", groups = {"regression", "transactionhistory"})
    public void GetPaidTransactionHistory() throws Exception {
        String CustomerID = "1020";
        String ResponseMessage = "message";
        transactionHistory.getPaidTransactionHistory(CustomerID,staticLoginAccessToken, ResponseMessage);
    }

    @Test(priority = 4, testName = "Verify system behavior when accessing pending and failed transaction history with valid parameters.", groups = {"regression", "transactionhistory"})
    public void GetPendingAndFailedTransactionHistory() throws Exception {
        String CustomerID = "1020";
        String ResponseMessage = "message";
        transactionHistory.getPendingAndFailedTransactionHistory(CustomerID,staticLoginAccessToken, ResponseMessage);
    }

    @Test(priority = 5, testName = "Verify system behavior when accessing Favourites transaction history with with valid parameters.", groups = {"regression", "transactionhistory"})
    public void GetTransactionHistoryByFavourites() throws Exception {
        String CustomerID = "1020";
        String ResponseMessage = "message";
        transactionHistory.getFavouritesNicknames(CustomerID,staticLoginAccessToken, ResponseMessage);
        transactionHistory.getTransactionHistoryByFavourites(CustomerID,staticLoginAccessToken, ResponseMessage);
    }

    @Test(priority = 6, testName = "Verify system behavior when accessing Utility Bills transaction history with with valid parameters.", groups = {"regression", "transactionhistory"})
    public void GetTransactionHistoryByUtilityBills() throws Exception {
        String CustomerID = "1020";
        String ResponseMessage = "message";
        transactionHistory.getListOfUserUtilityBills(CustomerID,staticLoginAccessToken, ResponseMessage);
        transactionHistory.getTransactionHistoryByUtilityBills(CustomerID,staticLoginAccessToken, ResponseMessage);
    }

    @Test(priority = 7, testName = "Verify system behavior when accessing specific Account Number transaction history with with valid parameters.", groups = {"regression", "transactionhistory"})
    public void GetTransactionHistoryByAccountNumber() throws Exception {
        String CustomerID = "1020";
        String ResponseMessage = "message";
        transactionHistory.getTransactionHistoryByAccountNumber(CustomerID,staticLoginAccessToken, ResponseMessage);
    }

    @Test(priority = 8, testName = "Verify system behavior when accessing transaction history with invalid access token.", groups = {"regression", "transactionhistory"})
    public void GetAllTransactionHistoryWithInvalidAccessToken() throws Exception {
        String CustomerID = "1020";
        String ResponseMessage = "message";
        transactionHistory.getAllTransactionHistoryWithInvalidAccessToken(CustomerID,accessToken, ResponseMessage);
    }

    @Test(priority = 9, testName = "Verify system behavior when accessing transaction history with non-existing favorites nickname.", groups = {"regression", "transactionhistory"})
    public void GetTransactionHistoryByNonExistingFavourites() throws Exception {
        String CustomerID = "1020";
        String ResponseMessage = "message";
        transactionHistory.getTransactionHistoryByNonExistingFavourites(CustomerID,staticLoginAccessToken, ResponseMessage);
    }

    @Test(priority = 10, testName = "Verify system behavior when accessing transaction history with non-existing utility bills.", groups = {"regression", "transactionhistory"})
    public void GetTransactionHistoryByNonExistingUtilityBills() throws Exception {
        String CustomerID = "1020";
        String ResponseMessage = "message";
        transactionHistory.getTransactionHistoryByNonExistingUtilityBills(CustomerID,staticLoginAccessToken, ResponseMessage);
    }

    @Test(priority = 11, testName = "Verify system behavior when accessing transaction history with non-existing account number.", groups = {"regression", "transactionhistory"})
    public void GetTransactionHistoryByNonExistingAccountNumber() throws Exception {
        String CustomerID = "1020";
        String ResponseMessage = "message";
        transactionHistory.getTransactionHistoryByNonExistingAccountNumber(CustomerID,staticLoginAccessToken, ResponseMessage);
    }
}
