package api;

import lombok.Getter;
import lombok.Setter;
import org.loit.api.CustomerRegistrationRequest;
import org.loit.api.EKycRequest;
import org.loit.utils.AccessTokenGenerator;
import org.loit.utils.StaticUserLoginAccessToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestListenerApi;

import static org.loit.utils.Utility.generateRandomMobileNumber;
@Listeners(TestListenerApi.class)
public class TestEKyc {
    EKycRequest EKycRequest = new EKycRequest();
    static CustomerRegistrationRequest CustomerRegistrationRequest = new CustomerRegistrationRequest();

    @Getter
    @Setter
    public static String otp;

    @Getter
    @Setter
    public static String staticLoginAccessToken;

    @Getter @Setter
    public static String accessToken;

    @Getter @Setter
    public static String randomMobileNumber;

    @Getter @Setter
    public static String loginAccessToken;


//    @BeforeClass

    @BeforeMethod (groups = {"regression", "eKycGroup"})
    public static void accessToken() throws Exception {
        AccessTokenGenerator accessTokenCreation = new AccessTokenGenerator();
        accessToken = accessTokenCreation.getAccessTokenCreation();

        randomMobileNumber = generateRandomMobileNumber();
        System.out.println("Random mobile number: " + randomMobileNumber);

        staticLoginAccessToken = StaticUserLoginAccessToken.StaticCustomerLoginAccessTokenCreation();
        //This is for get non-related/ invalid login access token

        AccessTokenGenerator loginAccessTokenCreation = new AccessTokenGenerator();
        loginAccessToken = loginAccessTokenCreation.getAccessTokenCreation();

        System.out.println("Invalid Login Access Token: " + staticLoginAccessToken + "\n" + "Login Access Token: " + loginAccessToken );

        String ResponseMessage = "message";
        String registrationInItPath = "src/test/resources/requests/customerRegistration/registration-init.json";
        String verifyOtpForRegistrationPath = "src/test/resources/requests/customerRegistration/verify-otp.json";
        String CusRegistrationCompleteLocalPath="src/test/resources/requests/customerRegistration/customer-registrationLocal.json";

        CustomerRegistrationRequest.cusRegInIt(accessToken,registrationInItPath,randomMobileNumber,ResponseMessage);
        CustomerRegistrationRequest.verifyOtpRequest(accessToken,verifyOtpForRegistrationPath,randomMobileNumber,ResponseMessage);
        CustomerRegistrationRequest.cusRegComplete(accessToken,CusRegistrationCompleteLocalPath,randomMobileNumber,ResponseMessage);
    }

    @Test(priority = 1, testName = "Verify system behavior when completing eKyc registration with valid parameters.", groups = {"regression", "eKycGroup"})
    public void CompleteEkyc() throws Exception{
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/registrationComplete.Json";
        EKycRequest.EKycRegistrationComplete(loginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

    @Test(priority = 2, testName = "Verify system behavior when completing eKyc registration with invalid login access token.",  groups = {"regression", "eKycGroup"})
    public void CompleteEkycWithInvalidAccessToken() throws Exception{//This test case is failing because there is bug in the API.
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/registrationCompleteInvalidParameters.Json";
        EKycRequest.EKycRegistrationCompleteWithInvalidParamiters(staticLoginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

    @Test(priority = 3, testName = "Verify system behavior when completing eKyc registration with invalid nic number.", groups =  {"regression", "eKycGroup"})
    public void CompleteEkycWithInvalidNicNumber() throws Exception{//This test case is failing because there is bug in the API.
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/registrationCompleteInvalidNicNumber.Json";
        EKycRequest.EKycRegistrationCompleteWithInvalidParamiters(loginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

    @Test(priority = 4, testName = "Verify system behavior when completing eKyc registration with null nic number.", groups =  {"regression", "eKycGroup"})
    public void CompleteEkycWithNullNicNumber() throws Exception{//This test case is failing because there is bug in the API.
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/registrationCompleteNullNicNumber.Json";
        EKycRequest.EKycRegistrationCompleteWithInvalidParamiters(loginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

    @Test(priority = 4, testName = "Verify system behavior when completing eKyc registration with null image paths", groups =  {"regression", "eKycGroup"})
    public void CompleteEkycWithNullImagePaths() throws Exception{//This test case is failing because there is bug in the API.
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/registrationCompleteNullImagePaths.Json";
        EKycRequest.EKycRegistrationCompleteWithInvalidParamiters(loginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

    @Test(priority = 5, testName = "Verify system behavior when completing eKyc registration with invalid image paths", groups =  {"regression", "eKycGroup"})
    public void CompleteEkycWithInvalidImagePaths() throws Exception{//This test case is failing because there is bug in the API.
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/registrationCompleteInvalidImagePaths.Json";
        EKycRequest.EKycRegistrationCompleteWithInvalidParamiters(loginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

    @Test(priority = 6, testName = "Verify system behavior when completing eKyc registration with an invalid string for image paths", groups =  {"regression", "eKycGroup"})
    public void CompleteEkycWithInvalidStringForImagePaths() throws Exception{//This test case is failing because there is bug in the API.
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/registrationCompleteInvalidStringForImagePaths.Json";
        EKycRequest.EKycRegistrationCompleteWithInvalidParamiters(loginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

    @Test(priority = 7, testName = "Verify system behavior when completing eKyc registration with an null value for passport", groups =  {"regression", "eKycGroup"})
    public void CompleteEkycWithNullValueForPassport() throws Exception{//This test case is failing because there is bug in the API.
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/registrationCompleteNullValueForPassport.Json";
        EKycRequest.EKycRegistrationCompleteWithInvalidParamiters(loginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

    @Test(priority = 8, testName = "Verify system behavior when completing eKyc registration with an invalid value for Gender", groups =  {"regression", "eKycGroup"})
    public void CompleteEkycWithInvalidValueForGender() throws Exception{//This test case is failing because there is bug in the API.
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/registrationCompleteInvalidValueForGender.Json";
        EKycRequest.EKycRegistrationCompleteWithInvalidParamiters(loginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

    @Test(priority = 9, testName = "Verify system behavior when completing eKyc registration with a null value for Gender", groups =  {"regression", "eKycGroup"})
    public void CompleteEkycWithNullValueForGender() throws Exception{//This test case is failing because there is bug in the API.
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/registrationCompleteNullValueForGender.Json";
        EKycRequest.EKycRegistrationCompleteWithInvalidParamiters(loginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

    @Test(priority = 10, testName = "Verify system behavior when completing eKyc registration with an invalid value for ekycDocType", groups =  {"regression", "eKycGroup"})
    public void CompleteEkycWithInvalidValueForEkycDocType() throws Exception{//This test case is failing because there is bug in the API.
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/completeEkycWithInvalidValueForEkycDocType.Json";
        EKycRequest.EKycRegistrationCompleteWithInvalidParamiters(loginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

    @Test(priority = 11, testName = "Verify system behavior when completing eKyc registration with a null value for ekycDocType", groups =  {"regression", "eKycGroup"})
    public void CompleteEkycWithNullValueForEkycDocType() throws Exception{//This test case is failing because there is bug in the API.
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/completeEkycWithNullValueForEkycDocType.Json";
        EKycRequest.EKycRegistrationCompleteWithInvalidParamiters(loginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

    @Test(priority = 12, testName = "Verify system behavior when completing eKyc registration with an invalid value for district", groups =  {"regression", "eKycGroup"})
    public void CompleteEkycWithInvalidValueForDistrict() throws Exception{//This test case is failing because there is bug in the API.
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/completeEkycWithInvalidValueForDistrict.Json";
        EKycRequest.EKycRegistrationCompleteWithInvalidParamiters(loginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

    @Test(priority = 13, testName = "Verify system behavior when completing eKyc registration with a null value for district", groups =  {"regression", "eKycGroup"})
    public void CompleteEkycWithNullValueForDistrict() throws Exception{//This test case is failing because there is bug in the API.
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/completeEkycWithNullValueForDistrict.Json";
        EKycRequest.EKycRegistrationCompleteWithInvalidParamiters(loginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

    @Test(priority = 14, testName = "Verify system behavior when completing eKyc registration with an invalid value for nationality", groups =  {"regression", "eKycGroup"})
    public void CompleteEkycWithInvalidValueForNationality() throws Exception{//This test case is failing because there is bug in the API.
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/completeEkycWithInvalidValueForNationality.Json";
        EKycRequest.EKycRegistrationCompleteWithInvalidParamiters(loginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

    @Test(priority = 15, testName = "Verify system behavior when completing eKyc registration with a null value for nationality", groups =  {"regression", "eKycGroup"})
    public void CompleteEkycWithNullValueForNationality() throws Exception{//This test case is failing because there is bug in the API.
        String cusRegistrationCompleteLocalPath="src/test/resources/requests/customer - eKyc/completeEkycWithNullValueForNationality.Json";
        EKycRequest.EKycRegistrationCompleteWithInvalidParamiters(loginAccessToken, cusRegistrationCompleteLocalPath, randomMobileNumber);
    }

}
