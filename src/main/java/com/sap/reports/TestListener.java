package com.sap.reports;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.sap.utility.CommonUtility;
import com.sap.utility.TestBase;

public class TestListener extends TestBase implements ITestListener {

	// Extent Report Declarations
	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public synchronized void onStart(ITestContext context) {
		System.out.println("Test Suite started!");
		System.out.println("*******************");
	}

	public synchronized void onFinish(ITestContext context) {
		System.out.println("*******************");
		System.out.println(("Test Suite is ending!"));
		extent.flush();
	}

	public synchronized void onTestStart(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " started!"));

		ExtentManager.extLogger = extent.createTest("ClassName: " + result.getTestClass().getRealClass().getSimpleName()
				+ "- Medthod: " + result.getMethod().getMethodName(), result.getMethod().getDescription());
		test.set(ExtentManager.extLogger);
	}

	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		System.out.println("*******************");
		test.get().pass("Test passed");
	}

	public synchronized void onTestFailure(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " failed!"));
		System.out.println("*******************");

		String failLog = "Test Case failed";
		Markup m = MarkupHelper.createLabel(failLog, ExtentColor.RED);
		test.get().log(Status.FAIL, m);

		// test.get().fail("Test Failed");
		// test.get().fail(result.getThrowable());
		String eMsg = Arrays.toString(result.getThrowable().getStackTrace());
		test.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception occured:Click to View"
				+ "</font>" + "</b>" + "</summary>" + eMsg.replaceAll(",", "<br>") + "</details>" + "\n");

		try {
			ExtentManager.extLogger.log(Status.FAIL,
					"Failed" + ExtentManager.extLogger
							.addScreenCaptureFromPath(CommonUtility.takeScreenShotWebReturnPath(driver,
									TestBase.sClassNameForScreenShot + "_" + result.getMethod().getMethodName())));
		} catch (IOException e) {
		}

	}

	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		test.get().skip(result.getThrowable());
		try {

			ExtentManager.extLogger.log(Status.SKIP,
					"SKIP: " + ExtentManager.extLogger
							.addScreenCaptureFromPath(CommonUtility.takeScreenShotWebReturnPath(driver,
									TestBase.sClassNameForScreenShot + "_" + result.getMethod().getMethodName())));
		} catch (IOException e) {
		}

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

}