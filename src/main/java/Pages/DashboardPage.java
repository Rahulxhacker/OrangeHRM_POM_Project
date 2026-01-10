package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BaseP;

public class DashboardPage extends BaseP {
	public DashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//h6[normalize-space()='Dashboard']")
	public WebElement dashBoard;

	@FindBy(xpath = "//span[normalize-space()='PIM']")
	public WebElement pimMenu;

	@FindBy(xpath = "//span[text() = 'Admin']")
	public WebElement adminMenu;

	public boolean isDashboardDisplayed() {
		return dashBoard.isDisplayed();
	}

	public PIMPage clickPIM() {
		pimMenu.click();
		return new PIMPage(driver);
	}

	public AdminPage clickAdmin() {
		adminMenu.click();

		return new AdminPage(driver);
	}

}
