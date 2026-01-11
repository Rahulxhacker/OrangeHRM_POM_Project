package TestCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseT;
import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.PIMPage;
import utils.DataProviderUtil;

public class PIMTest extends BaseT {

	@Test(dataProviderClass = DataProviderUtil.class, dataProvider = "dp")
	public void verifyAddEmployee(Hashtable<String, String> data) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("uName"), data.get("uPass"));

		DashboardPage dashboard = new DashboardPage(getDriver());
		dashboard.clickPIM();

		PIMPage pim = new PIMPage(getDriver());
		pim.addEmployee(data.get("empFName"), data.get("empLName"));

		Assert.assertTrue(pim.isEmployeeCreated(), data.get("empErrMsg"));

	}
}
