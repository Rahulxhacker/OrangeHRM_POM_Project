package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

	private ExtentManager() {
	}

	public static synchronized ExtentReports getExtent() {

		if (extent == null) {

			// ✅ Date created AT RUNTIME (not static)
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

			String reportDir = System.getProperty("user.dir") + "/reports";

			String reportPath = reportDir + "/Extent_" + timestamp + ".html";

			new File(reportDir).mkdirs();

			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

			reporter.config().setTheme(Theme.STANDARD);
			reporter.config().setDocumentTitle("Automation Report");
			reporter.config().setReportName("Test Execution Report");
			reporter.config().setEncoding("utf-8");

			extent = new ExtentReports();
			extent.attachReporter(reporter);

			extent.setSystemInfo("Execution Time", timestamp);
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Java", System.getProperty("java.version"));
		}
		return extent;
	}

	// 📸 Screenshot
	public static String captureScreenshot(WebDriver driver) {

		if (driver == null)
			return null;

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String name = "IMG_" + System.currentTimeMillis() + ".png";
		String path = System.getProperty("user.dir") + "/reports/screenshots/" + name;

		new File(System.getProperty("user.dir") + "/reports/screenshots").mkdirs();

		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
