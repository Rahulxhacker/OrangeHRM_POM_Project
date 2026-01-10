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

//	static Date d = new Date();
//	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	private static ExtentReports extent = ExtentManager.createInstance("./reports/");

	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {

		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + " :: " + result.getMethod().getMethodName());
		testReport.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		Markup m = MarkupHelper.createLabel("TEST CASE PASSED", ExtentColor.GREEN);
		testReport.get().pass(m);
	}

	@Override
	public void onTestFailure(ITestResult result) {

		testReport.get().fail(result.getThrowable());

		String path = ExtentManager.captureScreenshot(getDriver());

		try {
			testReport.get().fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Markup m = MarkupHelper.createLabel("TEST CASE FAILED", ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		Markup m = MarkupHelper.createLabel("TEST CASE SKIPPED", ExtentColor.YELLOW);
		testReport.get().skip(m);
	}

	@Override
	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}
}
