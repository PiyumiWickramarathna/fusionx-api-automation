package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;


import static org.reflections.Reflections.log;


public class TestListenerApi implements ITestListener {
    private static ExtentReports extent = new ExtentReports();
    private static ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/Report/[STG]Regression_Report.html");

    @Override
    public void onTestStart(ITestResult iTestResult) {
        ITestListener.super.onTestStart(iTestResult);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ITestListener.super.onTestSuccess(iTestResult);
        String passedTest = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName();

        log.info("Test '" + passedTest + "' has passed.");
        extent.attachReporter(reporter);
        extent.createTest(passedTest)
                .log(Status.PASS, "Test passed");
        extent.flush();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String failedTest = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName();
        log.error("Test '" + failedTest + "' has failed and a screenshot was taken.");
        extent.attachReporter(reporter);
        extent.createTest(failedTest)
                .log(Status.FAIL, "Fail with error: " + iTestResult.getThrowable());
        extent.flush();
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        String skippedTest = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName();
        log.error("Test '" + skippedTest + "' has been skipped.");
        extent.attachReporter(reporter);
        extent.createTest(skippedTest)
                .log(Status.SKIP, "Skipped due to: " + result.getThrowable());
        extent.flush();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }


    @Override
    public void onFinish(ITestContext context) {
    }
}