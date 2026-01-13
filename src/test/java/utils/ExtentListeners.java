package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import BaseTest.BaseT;

public class ExtentListeners extends BaseT implements ITestListener {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onStart(ITestContext context) {
		extent = ExtentManager.getExtent(); // ✅ Date generated here
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent
				.createTest(result.getTestClass().getName() + " :: " + result.getMethod().getMethodName());
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass(MarkupHelper.createLabel("TEST CASE PASSED", ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {

		test.get().fail(result.getThrowable());

		String path = ExtentManager.captureScreenshot(getDriver());

		if (path != null) {
			try {
				test.get().fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}
}
