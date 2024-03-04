package api;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.parser.ParseException;
import org.loit.api.QRPayRequest;
import org.loit.utils.LoginAccessToken;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestListenerApi;

import java.io.IOException;
@Listeners(TestListenerApi.class)
public class TestQRPay {

    QRPayRequest QRPayRequest = new QRPayRequest();


    @Getter
    @Setter
    public static String loginAccessToken;

    @BeforeClass (groups = {"regression", "qrpay"})
    public static void accessToken() throws IOException, ParseException {
        LoginAccessToken loginAccessTokenCreation = new LoginAccessToken();
        loginAccessToken = loginAccessTokenCreation.LoginAccessTokenCreation();
    }

    @Test (priority = 1, testName = "Verify response for QR Payments - valid data.", groups = {"regression", "qrpay"})
    public void qRPay() throws Exception{
        String deviceId = "18979871eacfbe56b3e9bd5f43a8db64";
        String instrumentRefNo = "0002010102110216423396000000133504155465020000001852632002816843001000000000001004602205204723053031445802LK5914HOLLYWOOD+CUTS6011ETHUL+KOTTE62130509LOLC_IPAY85310012IPAY_INS_REF0111Q000000218563048E8D";
        String instrumentPay2FeesPath = "src/test/resources/requests/customer-QRPay/instrumentPay2Fees.json";
        String instrumentPay2Path = "ssrc/test/resources/requests/customer-QRPay/instrumentPay2.json";
        QRPayRequest.getQRInstrumentRefNo(instrumentRefNo,loginAccessToken);
        QRPayRequest.instrumentPay2Fees(deviceId,loginAccessToken,instrumentPay2FeesPath);
        QRPayRequest.instrumentPay2(deviceId,loginAccessToken,instrumentPay2Path);
    }

    @Test(priority = 2, testName = "Verify the response for QR payments that exceed the amount limit.",groups = {"regression", "qrpay"})
    public void qRPayForAmountExceedLimit() throws Exception{
        String deviceId = "18979871eacfbe56b3e9bd5f43a8db64";
        String instrumentRefNo = "0002010102112632002816843001000000000000002242265204531153031445802LK5915MAJESCTIC+PLAZA6004TEST62130509LOLC_IPAY85310012IPAY_INS_REF0111Q00000036446304440B";
        String checkOtpEnablePath = "src/test/resources/requests/customer-QRPay/checkOtpEnableForExceedLimit.json";
        String instrumentPay2FeesPath = "src/test/resources/requests/customer-QRPay/instrumentPay2FeesForAmountExceedLimit.json";
        String verifyOtpPath = "src/test/resources/requests/customer-QRPay/verifyOtp.json";
        String instrumentPay2Path = "src/test/resources/requests/customer-QRPay/instrumentPay2ForAmountExceedLimit.json";
        QRPayRequest.getQRInstrumentRefNo(instrumentRefNo,loginAccessToken);
        QRPayRequest.instrumentPay2Fees(deviceId,loginAccessToken,instrumentPay2FeesPath);
        QRPayRequest.checkOtpEnableForAmountExceedLimit(deviceId,loginAccessToken,checkOtpEnablePath);
        QRPayRequest.verifyOtpForAmountExceedLimit(deviceId,loginAccessToken,verifyOtpPath);
        QRPayRequest.instrumentPay2(deviceId,loginAccessToken,instrumentPay2Path);
    }
    @Test (priority = 2, testName = "Verify the response for QR payments that exceed the amount limit for a constant OTP.",groups = {"regression", "qrpay"})
    public void qRPayForConstantOtp() throws Exception{
        String deviceId = "18979871eacfbe56b3e9bd5f43a8db64";
        String instrumentRefNo = "0002010102112632002816843001000000000000002242265204531153031445802LK5915MAJESCTIC+PLAZA6004TEST62130509LOLC_IPAY85310012IPAY_INS_REF0111Q00000036446304440B";
        String checkOtpEnablePath = "src/test/resources/requests/customer-QRPay/checkOtpEnableForExceedLimit.json";
        String instrumentPay2FeesPath = "src/test/resources/requests/customer-QRPay/instrumentPay2FeesForAmountExceedLimit.json";
        String verifyOtpPath = "src/test/resources/requests/customer-QRPay/verifyConstantOtp.json";
        String instrumentPay2Path = "src/test/resources/requests/customer-QRPay/instrumentPay2ForAmountExceedLimit.json";
        QRPayRequest.getQRInstrumentRefNo(instrumentRefNo,loginAccessToken);
        QRPayRequest.instrumentPay2Fees(deviceId,loginAccessToken,instrumentPay2FeesPath);
        QRPayRequest.checkOtpEnableForAmountExceedLimit(deviceId,loginAccessToken,checkOtpEnablePath);
        QRPayRequest.verifyOtpForConstant(deviceId,loginAccessToken,verifyOtpPath);
        QRPayRequest.instrumentPay2(deviceId,loginAccessToken,instrumentPay2Path);
    }
}
