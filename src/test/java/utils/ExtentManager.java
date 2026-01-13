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

	private static final String REPORT_DIR = System.getProperty("user.dir") + "/reports";

	private static final String SCREENSHOT_DIR = REPORT_DIR + "/screenshots";

	private static final String REPORT_PATH = REPORT_DIR + "/index.html";

	// 🔒 Prevent external instantiation
	private ExtentManager() {
	}

	// ✅ Create Extent Report instance
	public static synchronized ExtentReports getExtentReports() {

		if (extent == null) {

			// Create directories (Jenkins safe)
			new File(REPORT_DIR).mkdirs();
			new File(SCREENSHOT_DIR).mkdirs();

			ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_PATH);
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("Automation Test Report");
			spark.config().setReportName("OrangeHRM Automation Report");
			spark.config().setEncoding("utf-8");

			extent = new ExtentReports();
			extent.attachReporter(spark);

			// System info
			extent.setSystemInfo("Tester", "Rahul Kashyap");
			extent.setSystemInfo("Framework", "Selenium + TestNG");
			extent.setSystemInfo("Execution", "Jenkins");
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		}
		return extent;
	}

	// 📸 Capture Screenshot
	public static String captureScreenshot(WebDriver driver) {

		if (driver == null)
			return null;

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String screenshotName = "IMG_" + new Date().toString().replace(":", "_").replace(" ", "_") + ".png";

		String destPath = SCREENSHOT_DIR + "/" + screenshotName;

		try {
			FileUtils.copyFile(src, new File(destPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destPath;
	}
}
