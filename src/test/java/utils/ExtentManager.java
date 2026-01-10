package utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	public static String screenshotName;

	public static ExtentReports createInstance(String fileName) {

		ExtentSparkReporter reporter = new ExtentSparkReporter(fileName);
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setDocumentTitle("Automation Report");
		reporter.config().setReportName("OrangeHRM Automation");
		reporter.config().setEncoding("utf-8");

		extent = new ExtentReports();
		extent.attachReporter(reporter);

		extent.setSystemInfo("Automation Tester", "Rahul Arora");
		extent.setSystemInfo("Organization", "Way2Automation");
		extent.setSystemInfo("Build", "W2A-1234");

		return extent;
	}

	// ✅ THREAD-SAFE SCREENSHOT
	public static String captureScreenshot(WebDriver driver) {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".png";

		String path = System.getProperty("user.dir") + "/reports/screenshots/" + screenshotName;

		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}
