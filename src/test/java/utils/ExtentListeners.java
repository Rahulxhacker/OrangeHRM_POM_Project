package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import BaseTest.BaseT;

public class ExtentListeners extends BaseT implements ITestListener {

	private static ExtentReports extent = ExtentManager.getExtentReports();

	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	// 🚀 Test Started
	@Override
	public void onTestStart(ITestResult result) {

		String testName = result.getTestClass().getName() + " :: " + result.getMethod().getMethodName();

		ExtentTest extentTest = extent.createTest(testName);
		test.set(extentTest);
	}

	// ✅ Test Passed
	@Override
	public void onTestSuccess(ITestResult result) {

		Markup m = MarkupHelper.createLabel("TEST CASE PASSED", ExtentColor.GREEN);

		test.get().log(Status.PASS, m);
	}

	// ❌ Test Failed
	@Override
	public void onTestFailure(ITestResult result) {

		test.get().fail(result.getThrowable());

		String screenshotPath = ExtentManager.captureScreenshot(getDriver());

		if (screenshotPath != null) {
			try {
				test.get().fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Markup m = MarkupHelper.createLabel("TEST CASE FAILED", ExtentColor.RED);

		test.get().log(Status.FAIL, m);
	}

	// ⏭ Test Skipped
	@Override
	public void onTestSkipped(ITestResult result) {

		Markup m = MarkupHelper.createLabel("TEST CASE SKIPPED", ExtentColor.YELLOW);

		test.get().log(Status.SKIP, m);
	}

	// 🧹 Flush Report
	@Override
	public void onFinish(ITestContext context) {

		if (extent != null) {
			extent.flush();
		}
	}
}
