package org.loit.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:${user.dir}/src/test/resources/api-config.properties",
        "file:${user.dir}/src/test/resources/api-staging-config.properties",
        "file:${user.dir}/src/test/resources/api-dev-config.properties"
})
public interface ApiConfig extends Config {

    @Key("api.baseurl")
    String apiBaseUrl();
    @Key("token.url")
    String tokenUrl();

    @Key("list.users")
    String getUserEndpoint();

    @Key("create.customer")
    String postCusRegInIt();

    @Key("verify.otp")
    String postVerifyOtp();

    @Key("Customer.Registration")
    String postCustomerRegistration();

    @Key("accessToken.path")
    String pathAccessToken();
    @Key("cusLoginAccessToken.path")
    String pathCusLoginAccessToken();

    @Key("api.requests")
    String apiRequests();

    @Key("requestsCustomerRegistration")
    String customerRegistration();

    @Key("req.getAccessToken")
    String getAccessToken();


    @Key("update.users")
    String updateUserEndpoint();

    @Key("delete.users")
    String deleteUserEndpoint();

    @Key("registration.complete")
    String CusRegistrationCompleteLocalPath();

    @Key("login.mobileUrl")
    String CustomerLoginByMobileNo();

    @Key("kycDocumentType_Y")
    String kycDocumentType_Y();
    @Key("kycDocumentType_N")
    String kycDocumentType_N();

    @Key("customer.EKyc_FaceCompare")
    String Customer_EKyc_FaceCompare();

    @Key("customer.EKyc_Ocr")
    String Customer_EKyc_Ocr();

    @Key("EKycRegistrationComplete")
    String EKycRegistrationComplete();

    @Key("payment-source_N")
    String payment_source_N();

    @Key("bank_action")
    String bank_action();

    @Key("LocationCapturing")
    String Location_Capturing();

    @Key("AvailableTransLimit")
    String AvailableTransLimit();

    @Key("payment-source_Y")
    String payment_source_Y();
    @Key("fund-transfer")
    String fund_transfer();

    // ---------- Send Money -----------
    @Key("fund-transfer2fees")
    String fund_transfer2fees();
    @Key("checkOtpEnable")
    String checkOtpEnable();
    @Key("verifyOtp")
    String verifyOtp();
    @Key("fund-transfer2")
    String fund_transfer2();
    @Key ("makePayment2fees")
    String makePayment2fees();
    @Key ("makePayment2")
    String makePayment2();
    @Key("sendAnyone2fees")
    String sendAnyone2fees();
    @Key("sendAnyone2")
    String sendAnyone2();

    // ---------- End - Send Money -----------
    @Key("packageEnabledMerchants")
    String packageEnabledMerchants();
    @Key("packagesNew-Mobitel")
    String packagesNew_Mobitel();
    @Key("utilityProvider-1")
    String utilityProvider_1();
    @Key("utilityPayment2fees")
    String utilityPayment2fees();
    @Key("utilityPayment2")
    String utilityPayment2();
    @Key("favourites_exist")
    String favourites_exist();
    @Key("packagesNew-Dialog")
    String packagesNew_Dialog();
    @Key("utilityProvider-2")
    String utilityProvider_2();
    @Key("packagesNew-Hutch")
    String packagesNew_Hutch();
    @Key("utilityProvider-0")
    String utilityProvider_0();
    @Key("QRInstrumentRefNo")
    String QRInstrumentRefNo();
    @Key("instrumentPay2fees")
    String instrumentPay2fees();
    @Key("instrumentPay2")
    String instrumentPay2();
    @Key("staticUserLoginAccessTokenCreation.path")
    String pathStaticUserLoginAccessTokenCreation();
    @Key("getTransactionHistory")
    String TransactionHistoryPath();
    @Key("getReceivedTransactionHistory")
    String ReceivedTransactionHistoryPath();
    @Key("getPaidTransactionHistory")
    String PaidTransactionHistoryPath();
    @Key("getPendingAndFailedTransactionHistory")
    String PendingAndFailedTransactionHistoryPath();

    @Key("getFavouritesNicknames")
    String FavouritesNicknamesPath();

    @Key("getTransactionHistoryByFavourites")
    String TransactionHistoryByFavouritesPath();
    @Key("getBillers")
    String UtilityBillersPath();
    @Key("getTransactionHistoryByUtilityBills")
    String TransactionHistoryByUtilityBillsPath();
    @Key("getTransactionHistoryByAccountNumberPath")
    String TransactionHistoryByAccountNumberPath();

    // ----------Pay Bills  -----------
    @Key ("postCompleteTransaction")
    String postCompleteTransactionPath();
    // ---------- Send Money -----------
    @Key ("postCompleteMobileTransaction")
    String postCompleteMobileTransactionPath();








}