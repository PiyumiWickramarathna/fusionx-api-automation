package org.loit.api;

import io.restassured.response.Response;
import org.loit.api.assertwrapper.ResponseAssert;
import org.loit.config.factory.ApiConfigFactory;
import org.loit.utils.ResponseJsonSaverAndReader;

public class TransactionHistoryRequest {

    public void getTransactionHistory(String CustomerID,String staticLoginAccessToken, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().TransactionHistoryPath().replace("{Customer_ID}",CustomerID);
        Response response = ReqresApi.getTransactionHistory(endpoint, staticLoginAccessToken);
        System.out.println("Transaction History Response: " + response.asPrettyString());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
//                .hasKeyWithValue("responseCode", "000")
//                .hasKey("responseObject.transactionId[0]")
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void getReceivedTransactionHistory(String CustomerID,String staticLoginAccessToken, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().ReceivedTransactionHistoryPath().replace("{Customer_ID}",CustomerID);;
        Response response = ReqresApi.getTransactionHistory(endpoint, staticLoginAccessToken);
        System.out.println("Static Login Access Token: " + staticLoginAccessToken);
        System.out.println("Received Transaction History Response: " + response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void getPaidTransactionHistory(String CustomerID,String staticLoginAccessToken, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().PaidTransactionHistoryPath().replace("{Customer_ID}",CustomerID);;
        Response response = ReqresApi.getTransactionHistory(endpoint, staticLoginAccessToken);
        System.out.println("Paid Transaction History Response: " + response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
//                .hasKeyWithValue("responseCode", "000")
//                .hasKey("responseObject.transactionId[0]")
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void getPendingAndFailedTransactionHistory(String CustomerID,String staticLoginAccessToken, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().PendingAndFailedTransactionHistoryPath().replace("{Customer_ID}",CustomerID);;
        Response response = ReqresApi.getTransactionHistory(endpoint, staticLoginAccessToken);
        System.out.println("Pending And Failed Transaction History Response: " + response.prettyPrint());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .hasKey("responseObject.transactionId[0]")
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void getFavouritesNicknames(String CustomerID,String staticLoginAccessToken, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().FavouritesNicknamesPath().replace("{Customer_ID}",CustomerID);
        Response response = ReqresApi.getTransactionHistory(endpoint, staticLoginAccessToken);
        System.out.println("Favourites Nicknames Are: ");
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .hasKey("responseObject.id[0]")
                    .assertAll();
            ResponseJsonSaverAndReader.saveResponseAsJson(response, "src/test/resources/ResponsesTransactionHistory/Get-Favourites-Nicknames.json");
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void getTransactionHistoryByFavourites(String CustomerID,String staticLoginAccessToken, String ResponseMessage) throws Exception {
        String filePath = "src/test/resources/ResponsesTransactionHistory/Get-Favourites-Nicknames.json";
        String jsonPathExpression = "responseObject[0].nickName";
        String firstNickname = ResponseJsonSaverAndReader.getValueFromJsonFile(filePath, jsonPathExpression);

        String endpoint1 = ApiConfigFactory.getConfig().TransactionHistoryByFavouritesPath().replace("{Customer_ID}",CustomerID);
        String endpoint2 = endpoint1.replace("{favoriteNickname}", firstNickname);
        Response response = ReqresApi.getTransactionHistory(endpoint2, staticLoginAccessToken);
        System.out.println("Transaction History From "+firstNickname+" Are:"+ response.asPrettyString());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .hasKey("responseObject.transactionId[0]")
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void getTransactionHistoryByNonExistingFavourites (String CustomerID,String staticLoginAccessToken, String ResponseMessage) throws Exception {
        String nonExistingFavourite = "NonExistingFavourite";
        String endpoint1 = ApiConfigFactory.getConfig().TransactionHistoryByFavouritesPath().replace("{Customer_ID}", CustomerID);;
        String endpoint2 = endpoint1.replace("{favoriteNickname}", nonExistingFavourite);
        Response response = ReqresApi.getTransactionHistory(endpoint2, staticLoginAccessToken);
        System.out.println("Transaction History From NonExistingFavourite Are:"+ response.asPrettyString());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .arrayIsEmpty()
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void getListOfUserUtilityBills(String CustomerID,String staticLoginAccessToken, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().UtilityBillersPath().replace("{Customer_ID}",CustomerID);
        Response response = ReqresApi.getTransactionHistory(endpoint, staticLoginAccessToken);
        System.out.println("Frequently Used Utility Merchants are: ");
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .hasKey("responseObject.utilityMerchantCode[0]")
                    .assertAll();
            ResponseJsonSaverAndReader.saveResponseAsJson(response, "src/test/resources/ResponsesTransactionHistory/Get-Utility-Billers.json");
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void getTransactionHistoryByUtilityBills(String CustomerID,String staticLoginAccessToken, String ResponseMessage) throws Exception {
        String filePath = "src/test/resources/ResponsesTransactionHistory/Get-Utility-Billers.json";
        String jsonPathExpression = "responseObject[0].utilityMerchantCode";
        String firstMerchantId = ResponseJsonSaverAndReader.getValueFromJsonFile(filePath, jsonPathExpression);
        String endpoint1 = ApiConfigFactory.getConfig().TransactionHistoryByUtilityBillsPath().replace("{Customer_ID}",CustomerID);
        String endpoint2 =endpoint1.replace("{utilityMerchantCode}", firstMerchantId);
        Response response = ReqresApi.getTransactionHistory(endpoint2, staticLoginAccessToken);
        System.out.println("Transaction History From "+firstMerchantId+" Are:"+ response.asPrettyString());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .hasKey("responseObject.transactionId[0]")
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void getTransactionHistoryByNonExistingUtilityBills(String CustomerID,String staticLoginAccessToken, String ResponseMessage) throws Exception {
        String nonExistingMerchantId = "NonExistingMerchantId";
        String endpoint1 =ApiConfigFactory.getConfig().TransactionHistoryByUtilityBillsPath().replace("{Customer_ID}",CustomerID);
        String endpoint2 = endpoint1.replace("{utilityMerchantCode}", nonExistingMerchantId);
        Response response = ReqresApi.getTransactionHistory(endpoint2, staticLoginAccessToken);
        System.out.println("Transaction History From " + nonExistingMerchantId + " Are:" + response.asPrettyString());

        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .arrayIsEmpty()
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }


    public void getTransactionHistoryByAccountNumber(String CustomerID,String staticLoginAccessToken, String ResponseMessage) throws Exception {
        String accountNumberWithPreviousTransactions = "00110001943";
        String endpoint1 =ApiConfigFactory.getConfig().TransactionHistoryByAccountNumberPath().replace("{Customer_ID}",CustomerID);
        String endpoint2 = endpoint1.replace("{accountNumber}", accountNumberWithPreviousTransactions);
        Response response = ReqresApi.getTransactionHistory(endpoint2, staticLoginAccessToken);
        System.out.println("Transaction History From "+accountNumberWithPreviousTransactions+" Account Are:"+ response.asPrettyString());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .hasKey("responseObject.transactionId[0]")
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void getTransactionHistoryByNonExistingAccountNumber(String CustomerID,String staticLoginAccessToken, String ResponseMessage) throws Exception {
        String nonExistingAccountNumber = "09118500789";
        String endpoint1 =ApiConfigFactory.getConfig().TransactionHistoryByAccountNumberPath().replace("{Customer_ID}",CustomerID);
        String endpoint2 = endpoint1.replace("{accountNumber}", nonExistingAccountNumber);
        Response response = ReqresApi.getTransactionHistory(endpoint2, staticLoginAccessToken);
        System.out.println("Transaction History From "+nonExistingAccountNumber+" Account Are:"+ response.asPrettyString());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "000")
                    .arrayIsEmpty()
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

    public void getAllTransactionHistoryWithInvalidAccessToken(String CustomerID,String loginAccessToken, String ResponseMessage) throws Exception {
        String endpoint = ApiConfigFactory.getConfig().TransactionHistoryPath().replace("{Customer_ID}",CustomerID);
        Response response = ReqresApi.getTransactionHistory(endpoint, loginAccessToken);
        System.out.println("Transaction History with invalid access token Response: " + response.asPrettyString());
        try {
            ResponseAssert.assertThat(response)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseCode", "999")
                    .hasKey("responseObject.errorCode[0]")
                    .assertAll();
        }
        catch(Exception e){
            throw new Exception(response.prettyPrint());
        }
    }

}
