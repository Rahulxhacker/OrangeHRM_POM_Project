package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BaseP;

public class LoginPage extends BaseP {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//input[@placeholder='Username']")
	public WebElement uName;

	@FindBy(xpath = "//input[@placeholder='Password']")
	public WebElement uPass;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	public WebElement lgnBtn;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
	public WebElement errMsg;

	public DashboardPage doLogin(String userName, String passWord) {
		uName.sendKeys(userName);
		uPass.sendKeys(passWord);
		lgnBtn.click();
		return new DashboardPage(driver);
	}

	public boolean isErrorDisplayed() {
		return errMsg.isDisplayed();
	}

}
