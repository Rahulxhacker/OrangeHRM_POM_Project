package TestCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseT;
import Pages.DashboardPage;
import Pages.DirectoryPage;
import Pages.LoginPage;
import utils.DataProviderUtil;

public class DirectoryTest extends BaseT {

	@Test(dataProviderClass = DataProviderUtil.class, dataProvider = "dp")
	public void verifyDirectoryTest(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("uDirect"), data.get("uDirectPass"));

		DashboardPage dashboardPage = new DashboardPage(getDriver());
		dashboardPage.clickDirectory();

		DirectoryPage directoryPage = new DirectoryPage(getDriver());

		Assert.assertTrue(directoryPage.isDirectoryDisplayed(), data.get("directErrMsg"));
	}

	@Test(dataProvider = "dp", dataProviderClass = DataProviderUtil.class)
	public void verifyDirectoryUser(Hashtable<String, String> data) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("user"), data.get("pass"));

		DashboardPage dashboardPage = new DashboardPage(getDriver());
		dashboardPage.clickDirectory();

		DirectoryPage directoryPage = new DirectoryPage(getDriver());

		directoryPage.Directory(data.get("dName"));

		Assert.assertTrue(directoryPage.isDirectoryDisplayed(), data.get("dErr"));

	}

}
