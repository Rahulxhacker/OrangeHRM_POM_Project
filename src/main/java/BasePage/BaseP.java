package BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseP {
	
	
	
	public WebDriver driver;

	public BaseP(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
