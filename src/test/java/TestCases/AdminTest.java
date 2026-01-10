package TestCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseT;
import Pages.AdminPage;
import Pages.DashboardPage;
import Pages.LoginPage;
import utils.DataProviderUtil;

public class AdminTest extends BaseT {

	@Test(dataProviderClass = DataProviderUtil.class, dataProvider = "dp")
	public void verifyAdminPageNavigation(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("Uadmin"), data.get("UadminPass"));

		DashboardPage dashboardPage = new DashboardPage(getDriver());
		dashboardPage.clickAdmin();

		AdminPage adminPage = new AdminPage(getDriver());
		Assert.assertTrue(adminPage.isAdminDisplayed(), data.get("adminErr"));

	}

	@Test(dataProviderClass = DataProviderUtil.class, dataProvider = "dp")
	public void verifySearchSystemUser(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("sUser"), data.get("sPass"));

		DashboardPage dashboardPage = new DashboardPage(getDriver());
		dashboardPage.clickAdmin();

		AdminPage adminPage = new AdminPage(getDriver());
		adminPage.searchUser(data.get("searchUser"));

		Assert.assertTrue(adminPage.isUserDisplayed(), data.get("notFoundMsg"));
	}

}
