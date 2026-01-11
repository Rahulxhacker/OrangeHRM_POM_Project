package BaseTest;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ExcelReader;

public class BaseT {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public ExcelReader excel = new ExcelReader(
			"C:\\Users\\Rahul Kashyap\\eclipse-workspace\\OrangeHRM_POM\\src\\test\\resources\\excel\\OrangeHRM_Data.xlsx");

	public WebDriver getDriver() {
		return driver.get();
	}

	@BeforeMethod
	public void setUp() throws MalformedURLException {

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		ops.addArguments("--disable-infobars");

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.password_manager_leak_detection", false);

		ops.setExperimentalOption("prefs", prefs);
		ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

//		driver.set(new RemoteWebDriver(URI.create("http://192.168.47.116:4444/wd/hub").toURL(), ops));
		driver.set(new ChromeDriver(ops));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().window().maximize();
		getDriver().get("https://opensource-demo.orangehrmlive.com/");
	}

	@AfterMethod
	public void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
			driver.remove();
		}
	}

}
