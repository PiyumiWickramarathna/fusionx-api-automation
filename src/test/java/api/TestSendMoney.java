package api;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.parser.ParseException;
import org.loit.api.SendMoneyRequest;
import org.loit.utils.LoginAccessToken;
import org.loit.utils.StaticUserLoginAccessToken;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestListenerApi;

import java.io.IOException;
@Listeners(TestListenerApi.class)
public class TestSendMoney {

    SendMoneyRequest SendMoneyBankTransaction = new SendMoneyRequest();

    SendMoneyRequest sendMoneyMobileTransaction = new SendMoneyRequest();
    @Getter
    @Setter
    public static String loginAccessToken;
    @Getter
    @Setter

    public static String staticLoginAccessToken;



    @BeforeClass (groups = {"regression", "sendmoney"})
    public static void accessToken() throws IOException, ParseException {
        LoginAccessToken loginAccessTokenCreation = new LoginAccessToken();
        loginAccessToken = loginAccessTokenCreation.LoginAccessTokenCreation();
        staticLoginAccessToken = StaticUserLoginAccessToken.StaticCustomerLoginAccessTokenCreation();
    }

    @Test (priority = 1, testName = "Verify the response for the bank transaction method used to send money with valid data.", groups = {"regression", "sendmoney"})
    public void sendMoneyForBankTransaction() throws Exception{
        String deviceId = "18979871eacfbe56b3e9bd5f43a8db64";
        String fundTransfer2FeesPath = "src/test/resources/requests/customer-sendMoney/BankTransactions/fundTransfer2fees.json";
        String fundTransfer2Path = "src/test/resources/requests/customer-sendMoney/BankTransactions/fundTransfer2.json";
        SendMoneyBankTransaction.fundTransfer2feesForSendMoneyBankTransaction(deviceId,loginAccessToken,fundTransfer2FeesPath);
        SendMoneyBankTransaction.fundTransfer2ForSendMoneyBankTransaction(deviceId,loginAccessToken,fundTransfer2Path);
    }
    @Test (priority = 1, testName = "Verify the response for the mobile transaction method iPay customers use to send money with valid data.", groups = {"regression", "sendmoney"})
    public void sendMoneyForMobileTransactionIPayCustomers() throws Exception{
        String deviceId = "18979871eacfbe56b3e9bd5f43a8db64";
        String makePayment2FeesPath = "src/test/resources/requests/customer-sendMoney/MobileTransactions/makePayment2fees.json";
        String makePayment2Path = "src/test/resources/requests/customer-sendMoney/MobileTransactions/makePayment2.json";
        SendMoneyBankTransaction.makePayment2feesForSendMoneyMobileTransactionForIPayCustomers(deviceId,loginAccessToken,makePayment2FeesPath);
        SendMoneyBankTransaction.makePayment2ForSendMoneyMobileTransactionForIPayCustomers(deviceId,loginAccessToken,makePayment2Path);
    }

    @Test (priority = 1, testName = "Verify the response for the mobile transaction method non-iPay customers use to send money with valid data.", groups = {"regression", "sendmoney"})
    public void sendMoneyForMobileTransactionNonIPayCustomers() throws Exception{
        String deviceId = "18979871eacfbe56b3e9bd5f43a8db64";
        String sendAnyone2FeesPath = "src/test/resources/requests/customer-sendMoney/MobileTransactions/sendAnyone2fees.json";
        String sendAnyone2Path = "src/test/resources/requests/customer-sendMoney/MobileTransactions/sendAnyone2.json";
        SendMoneyBankTransaction.sendAnyone2feesForSendMoneyMobileTransactionForNonIPayCustomers(deviceId,loginAccessToken,sendAnyone2FeesPath);
        SendMoneyBankTransaction.sendAnyone2ForSendMoneyMobileTransactionForNonIPayCustomers(deviceId,loginAccessToken,sendAnyone2Path);
    }

    @Test (priority = 2, testName = "Verify the response for the bank transaction method used to send money with an exceeded amount limit for a constant OTP.", groups = {"regression", "sendmoney"})
    public void sendMoneyForBankTransactionForAmountExceedLimitWithConstantVerifyOtp() throws Exception{
        String deviceId = "18979871eacfbe56b3e9bd5f43a8db64";
        String fundTransfer2FeesPath = "src/test/resources/requests/customer-sendMoney/BankTransactions/fundTransfer2feesForAmountExceedLimit.json";
        String fundTransfer2Path = "src/test/resources/requests/customer-sendMoney/BankTransactions/fundTransfer2ForAmountExceedLimit.json";
        String checkOtpEnablePath = "src/test/resources/requests/customer-sendMoney/BankTransactions/checkOtpEnableForExceedLimit.json";
        String verifyOtpPath = "src/test/resources/requests/customer-sendMoney/BankTransactions/verifyOtp.json";
        SendMoneyBankTransaction.fundTransfer2feesForSendMoneyBankTransaction(deviceId,loginAccessToken,fundTransfer2FeesPath);
        SendMoneyBankTransaction.checkOtpEnableForAmountExceedLimit(deviceId,loginAccessToken,checkOtpEnablePath);
        SendMoneyBankTransaction.verifyOtpForConstant(deviceId,loginAccessToken,verifyOtpPath);
        SendMoneyBankTransaction.fundTransfer2ForSendMoneyBankTransaction(deviceId,loginAccessToken,fundTransfer2Path);
    }

    @Test (priority = 2, testName = "Verify the response for the bank transaction method used to send money with an exceeded amount limit.", groups = {"regression", "sendmoney"})
    public void sendMoneyForBankTransactionForAmountExceedLimit() throws Exception{
        String deviceId = "18979871eacfbe56b3e9bd5f43a8db64";
        String fundTransfer2FeesPath = "src/test/resources/requests/customer-sendMoney/BankTransactions/fundTransfer2feesForAmountExceedLimit.json";
        String fundTransfer2Path = "src/test/resources/requests/customer-sendMoney/BankTransactions/fundTransfer2ForAmountExceedLimit.json";
        String checkOtpEnablePath = "src/test/resources/requests/customer-sendMoney/BankTransactions/checkOtpEnableForExceedLimit.json";
        String verifyOtpPath = "src/test/resources/requests/customer-sendMoney/BankTransactions/verifyConstantOtp.json";
        SendMoneyBankTransaction.fundTransfer2feesForSendMoneyBankTransaction(deviceId,loginAccessToken,fundTransfer2FeesPath);
        SendMoneyBankTransaction.checkOtpEnableForAmountExceedLimit(deviceId,loginAccessToken,checkOtpEnablePath);
        SendMoneyBankTransaction.verifyOtpForConstant(deviceId,loginAccessToken,verifyOtpPath);
        SendMoneyBankTransaction.fundTransfer2ForSendMoneyBankTransaction(deviceId,loginAccessToken,fundTransfer2Path);
    }

//    @Test(priority = 1, testName = "Verify system behavior when transfer money to mobile with valid parameters.", groups = {"regression", "sendmoney"})
//    public void BankTransferMobile() throws Exception {
//        String ResponseMessage = "message";
//        String completeTransactionPath = "src/test/resources/requests/sendMoney-Mobile/completeTransaction.json";
//        sendMoneyMobileTransaction.postCompleteMobileTransaction(staticLoginAccessToken,completeTransactionPath, ResponseMessage);
//    }

    @Test(priority = 2, testName = "Verify system behavior when transfer money to mobile with exceed amount.", groups = {"regression", "sendmoney"})
    public void BankTransferMobileExceedAmount() throws Exception {
        String ResponseMessage = "message";
        String completeTransactionPath = "src/test/resources/requests/sendMoney-Mobile/completeTransaction-with-exceed-amount.json";
        sendMoneyMobileTransaction.postCompleteMobileTransactionWithExceedAmount(staticLoginAccessToken,completeTransactionPath, ResponseMessage);
    }

    @Test(priority = 3, testName = "Verify system behavior when transfer money to mobile with invalid Access Token.", groups = {"regression", "sendmoney"})
    public void BankTransferMobileInvalidAccessToken() throws Exception { // This test case is failing because of the bug in the API
        String ResponseMessage = "message";
        String completeTransactionPath = "src/test/resources/requests/sendMoney-Mobile/completeTransaction-with-invalid-access-token.json";
        sendMoneyMobileTransaction.postCompleteMobileTransactionWithInvalidAccessToken(loginAccessToken,completeTransactionPath, ResponseMessage);
    }

    @Test(priority = 4, testName = "Verify system behavior when transfer money to mobile with invalid device ID.", groups = {"regression", "sendmoney"})
    public void BankTransferMobileInvalidDeviceID() throws Exception {
        String ResponseMessage = "message";
        String completeTransactionPath = "src/test/resources/requests/sendMoney-Mobile/completeTransaction-with-invalid-device-id.json";
        sendMoneyMobileTransaction.postCompleteMobileTransactionWithInvalidDeviceID(staticLoginAccessToken,completeTransactionPath, ResponseMessage);
    }

}

