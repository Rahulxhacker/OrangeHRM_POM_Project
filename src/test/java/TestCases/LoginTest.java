package TestCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseT;
import Pages.DashboardPage;
import Pages.LoginPage;
import utils.DataProviderUtil;

public class LoginTest extends BaseT {

	@Test(dataProviderClass = DataProviderUtil.class, dataProvider = "dp")
	public void verifyValidLogin(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("name"), data.get("password"));

		DashboardPage dashboard = new DashboardPage(getDriver());
		Assert.assertTrue(dashboard.isDashboardDisplayed(), data.get("failedMsg"));

	}

	@Test(dataProviderClass = DataProviderUtil.class, dataProvider = "dp")
	public void verifyInvalidLogin(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("wname"), data.get("wpassword"));
		Assert.assertTrue(loginPage.isErrorDisplayed(), data.get("errMsg"));
	}

}
