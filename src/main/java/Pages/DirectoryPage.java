package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BaseP;

public class DirectoryPage extends BaseP {

	public DirectoryPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//input[@placeholder='Type for hints...']")
	public WebElement empName;

	@FindBy(xpath = "//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]")
	public WebElement jobTitle;

	@FindBy(xpath = "//span[normalize-space()='Chief Financial Officer']")
	public WebElement jobType;

	@FindBy(xpath = "//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]")
	public WebElement location;

	@FindBy(xpath = "//span[normalize-space()='New York Sales Office']")
	public WebElement whichLocation;

	@FindBy(xpath = "//button[normalize-space()='Search']")
	public WebElement submitBTN;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-directory-card-header --break-words']")
	public WebElement resultEmpName;

	@FindBy(xpath = "//h6[normalize-space()='Directory']")
	public WebElement directoryHeader;

	public boolean isDirectoryDisplayed() {
		return directoryHeader.isDisplayed();
	}

	public void Directory(String eName) throws InterruptedException {
		String keyChord = Keys.chord(Keys.DOWN, Keys.ENTER);
		empName.sendKeys(eName);

		empName.sendKeys(keyChord);

		jobTitle.click();

		jobType.click();

		location.click();

		whichLocation.click();

		submitBTN.click();
	}

	public boolean reultEmp() {
		return resultEmpName.isDisplayed();
	}
}
