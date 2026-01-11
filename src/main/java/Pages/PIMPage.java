package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BaseP;

public class PIMPage extends BaseP {
	public PIMPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//button[normalize-space()='Add']")
	public WebElement addEmpbtn;

	@FindBy(xpath = "//input[@placeholder='First Name']")
	public WebElement firstName;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	public WebElement lastName;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	public WebElement saveBtn;

	@FindBy(xpath = "//a[normalize-space()='Personal Details']")
	public WebElement personalDetailHeader;

	public PIMPage addEmployee(String fname, String lname) throws InterruptedException {
		addEmpbtn.click();
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		Thread.sleep(2000);
		saveBtn.click();
		return this;
	}

	public boolean isEmployeeCreated() {
		return personalDetailHeader.isDisplayed();
	}

}
