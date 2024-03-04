package api;


import lombok.Getter;
import lombok.Setter;
import org.json.simple.parser.ParseException;
import org.loit.api.CustomerRegistrationRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.loit.utils.AccessTokenGenerator;
import org.loit.utils.CustomerLoginAccessTokenGen;
import utils.TestListenerApi;
import java.io.IOException;
import static org.loit.utils.Utility.generateRandomMobileNumber;

@Listeners(TestListenerApi.class)
public class TestCustomerRegistration {
    @Getter
    @Setter
    public static String otp;

    CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest();

    @Getter @Setter
    public static String accessToken;

    @Getter @Setter
    public static String randomMobileNumber;

    @Getter @Setter
    public static String cusLoginAccess;


    @BeforeClass (groups = {"regression", "CustomerRegistrationGroup", "test"})
    public static void accessToken() throws IOException, ParseException {
        AccessTokenGenerator accessTokenCreation = new AccessTokenGenerator();
        accessToken = accessTokenCreation.getAccessTokenCreation();
        System.out.println("access Token:"+accessToken);

        CustomerLoginAccessTokenGen CusLogAccessCreation = new CustomerLoginAccessTokenGen();
        cusLoginAccess = CusLogAccessCreation.CustomerLoginAccessTokenCreation();
        System.out.println("cus access Token:"+cusLoginAccess);
    }

    @BeforeMethod (groups = {"regression", "CustomerRegistrationGroup", "test"})
    public static void mobileNumber() throws IOException,ParseException{
        randomMobileNumber = generateRandomMobileNumber();
        System.out.println("Random mobile number: " + randomMobileNumber);

    }
    @Test (priority = 1, testName = "Verify response for customer registration - valid data.", groups = {"regression", "CustomerRegistrationGroup", "test"})
    public void CustomerRegistrationValidData() throws Exception {

        String ResponseMessage = "message";
        String registrationInItPath = "src/test/resources/requests/customerRegistration/registration-init.json";
        String verifyOtpForRegistrationPath = "src/test/resources/requests/customerRegistration/verify-otp.json";
        String CusRegistrationCompleteLocalPath="src/test/resources/requests/customerRegistration/customer-registrationLocal.json";

        customerRegistrationRequest.cusRegInIt(accessToken,registrationInItPath,randomMobileNumber,ResponseMessage);
        customerRegistrationRequest.verifyOtpRequest(accessToken,verifyOtpForRegistrationPath,randomMobileNumber,ResponseMessage);
        customerRegistrationRequest.cusRegComplete(accessToken,CusRegistrationCompleteLocalPath,randomMobileNumber,ResponseMessage);
//    customerRegistrationRequest.loginByMobileNo(accessToken, randomMobileNumber);

    }
    @Test(priority = 2, testName = "Verify response for customer registration with no email.",groups = {"regression", "CustomerRegistrationGroup", "test"})
    public void CustomerRegistrationValidDataNegativeNoEmail() throws Exception {
        String ResponseMessage = "errorMessage";
        String registrationInItPath = "src/test/resources/requests/customerRegistration/registration-init-noemail.json";
        customerRegistrationRequest.cusRegInIt(accessToken,registrationInItPath,randomMobileNumber,ResponseMessage);
    }
    @Test(priority = 2, testName = "Verify response for customer registration with no mobile number.",groups = {"regression", "CustomerRegistrationGroup", "test"})
    public void CustomerRegistrationValidDataNegativeNoMobile() throws Exception {
        randomMobileNumber = "";
        String ResponseMessage = "errorMessage";
        String registrationInItPath = "src/test/resources/requests/customerRegistration/registration-init-nomobile.json";
        customerRegistrationRequest.cusRegInIt(accessToken, registrationInItPath, randomMobileNumber, ResponseMessage);
    }
    @Test(priority = 2, testName = "Verify response for customer registration with a mobile number of less than 10 digits.",groups = {"regression", "CustomerRegistrationGroup", "test"})
    public void CustomerRegistrationValidDataNegativeMobileNoLessThan10Digits() throws Exception {
        randomMobileNumber = "07031896";
        String ResponseMessage = "errorMessage";
        String registrationInItPath = "src/test/resources/requests/customerRegistration/registration-init-mobileno-lessthan10dig.json";
        customerRegistrationRequest.cusRegInIt(accessToken, registrationInItPath, randomMobileNumber, ResponseMessage);
    }

    @Test(priority = 2, testName = "Verify response for customer registration with a mobile number of more than 10 digits.",groups = {"regression", "CustomerRegistrationGroup", "test"})
    public void CustomerRegistrationValidDataNegativeMobileNoMoreThan10Digits() throws Exception {
        randomMobileNumber = "07031896158936";
        String ResponseMessage = "errorMessage";
        String registrationInItPath = "src/test/resources/requests/customerRegistration/registration-init-mobileno-morethan10dig.json";
        customerRegistrationRequest.cusRegInIt(accessToken, registrationInItPath, randomMobileNumber, ResponseMessage);
    }

    @Test(priority = 2, testName = "Verify response for customer registration with a mobile number containing text.",groups = {"regression", "CustomerRegistrationGroup", "test"})
    public void CustomerRegistrationValidDataNegativeMobileNoWithTexts() throws Exception {
        randomMobileNumber = "Mwrt0703189";
        String ResponseMessage = "errorMessage";
        String registrationInItPath = "src/test/resources/requests/customerRegistration/registration-init-mobileno-morethan10dig.json";
        customerRegistrationRequest.cusRegInIt(accessToken, registrationInItPath, randomMobileNumber, ResponseMessage);
    }
    @Test(priority = 3, testName = "Verify response for customer registration with an incorrect OTP.",groups = {"regression", "CustomerRegistrationGroup", "test"})
    public void CustomerRegistrationValidDataIncorrectOtp() throws Exception {
        String ResponseMessage = "message";
        String registrationInItPath = "src/test/resources/requests/customerRegistration/registration-init.json";
        customerRegistrationRequest.cusRegInIt(accessToken,registrationInItPath,randomMobileNumber,ResponseMessage);
        otp="5689";
        String ResponseMessageIncorrectOtp = "errorMessage";
        String verifyOtpForRegistrationPath = "src/test/resources/requests/customerRegistration/verify-otp-incorrect.json";
        customerRegistrationRequest.verifyOtpRequestReSend(accessToken,verifyOtpForRegistrationPath,randomMobileNumber,ResponseMessageIncorrectOtp);
    }
    @Test(priority = 4, testName = "Verify response for customer registration with existing mobile number.",groups = {"regression", "CustomerRegistrationGroup", "test"})
    public void CustomerRegistrationCompleteForExistingMobile() throws Exception {
        randomMobileNumber = "0743696122";
        String ResponseMessageForInCompleteReg = "errorMessage";
        String CusRegistrationCompleteLocalPath ="src/test/resources/requests/customerRegistration/customer-registrationLocal-existingMobile.Json";
        customerRegistrationRequest.cusRegCompleteNegative(accessToken,CusRegistrationCompleteLocalPath,randomMobileNumber,ResponseMessageForInCompleteReg);
    }
    @Test(priority = 4, testName = "Verify response for customer registration with invalid language.",groups = {"regression", "CustomerRegistrationGroup", "test"})
    public void CustomerRegistrationCompleteForInvalidLanguage() throws Exception {
        String ResponseMessage = "message";
        String ResponseMessageForInCompleteReg = "errorMessage";

        String registrationInItPath = "src/test/resources/requests/customerRegistration/registration-init.json";
        String verifyOtpForRegistrationPath = "src/test/resources/requests/customerRegistration/verify-otp.json";
        String CusRegistrationCompleteLocalPath ="src/test/resources/requests/customerRegistration/customer-registrationLocal-IncorrectLanguage.json";

        customerRegistrationRequest.cusRegInIt(accessToken,registrationInItPath,randomMobileNumber,ResponseMessage);
        customerRegistrationRequest.verifyOtpRequest(accessToken,verifyOtpForRegistrationPath,randomMobileNumber,ResponseMessage);
        customerRegistrationRequest.cusRegCompleteNegative(accessToken,CusRegistrationCompleteLocalPath,randomMobileNumber,ResponseMessageForInCompleteReg);
    }

    @Test(priority = 4, testName = "Verify response for customer registration with invalid or empty NIC.",groups = {"regression", "CustomerRegistrationGroup", "test"})
    public void CustomerRegistrationCompleteForInvalidNIC() throws Exception {
        String ResponseMessage = "message";
        String ResponseMessageForInCompleteReg = "errorMessage";

        String registrationInItPath = "src/test/resources/requests/customerRegistration/registration-init.json";
        String verifyOtpForRegistrationPath = "src/test/resources/requests/customerRegistration/verify-otp.json";
        String CusRegistrationCompleteLocalPath ="src/test/resources/requests/customerRegistration/customer-registrationLocal-InvalidNIC.json";

        customerRegistrationRequest.cusRegInIt(accessToken,registrationInItPath,randomMobileNumber,ResponseMessage);
        customerRegistrationRequest.verifyOtpRequest(accessToken,verifyOtpForRegistrationPath,randomMobileNumber,ResponseMessage);
        customerRegistrationRequest.cusRegCompleteNegative(accessToken,CusRegistrationCompleteLocalPath,randomMobileNumber,ResponseMessageForInCompleteReg);
    }
    @Test(priority = 4, testName = "Verify response for customer registration with invalid nationality.",groups = {"regression", "CustomerRegistrationGroup", "test"})
    public void CustomerRegistrationCompleteForInvalidNationality() throws Exception {
        String ResponseMessage = "message";
        String ResponseMessageForInCompleteReg = "errorMessage";

        String registrationInItPath = "src/test/resources/requests/customerRegistration/registration-init.json";
        String verifyOtpForRegistrationPath = "src/test/resources/requests/customerRegistration/verify-otp.json";
        String CusRegistrationCompleteLocalPath ="src/test/resources/requests/customerRegistration/customer-registrationLocal-Invalid Nationality.json";

        customerRegistrationRequest.cusRegInIt(accessToken,registrationInItPath,randomMobileNumber,ResponseMessage);
        customerRegistrationRequest.verifyOtpRequest(accessToken,verifyOtpForRegistrationPath,randomMobileNumber,ResponseMessage);
        customerRegistrationRequest.cusRegCompleteNegative(accessToken,CusRegistrationCompleteLocalPath,randomMobileNumber,ResponseMessageForInCompleteReg);
    }

}
