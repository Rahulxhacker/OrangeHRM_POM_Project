package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BaseP;

public class AdminPage extends BaseP {

	public AdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//h6[normalize-space()='Admin']")
	public WebElement adminHeader;

	@FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
	public WebElement userNameField;

	@FindBy(xpath = "//button[normalize-space()='Search']")
	public WebElement searchBtn;

	@FindBy(xpath = "//button[normalize-space()='Add']")
	public WebElement addBtn;

	@FindBy(xpath = "//div[@class='oxd-table-body']//div[@role='row']")
	public WebElement userResult;

	public boolean isAdminDisplayed() {
		return adminHeader.isDisplayed();
	}

	public void searchUser(String userName) {
		userNameField.sendKeys(userName);
		searchBtn.click();
	}

	public boolean isUserDisplayed() {
		return userResult.isDisplayed();
	}

	public void clickAddUser() {
		addBtn.click();
	}
}
