package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 * Data is valid = login success -test pass -logout
 * Data is valid = login failed -test fail 
 * Data is invalid = login success -test fail -logout
 * Data is invalid = login success -test pass 
 * 
 * */

public class TC_003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void verify_LoginDDT(String email, String password, String result) {
		try {
			logger.info("*********TC__03__LoginDDT");

			HomePage hp = new HomePage(driver);

			hp.ClickOnLoginButton();
			logger.info("Clicked on LoginButton");

			LoginPage lp = new LoginPage(driver);

			lp.enterEmailId(email);
			logger.info("Email Address entered");

			lp.enterLoginPassWord(password);
			logger.info("password for login entered");

			lp.clickOnLOgInButton();
			logger.info("clicked on login Button");

			MyAccountPage map = new MyAccountPage(driver);

			boolean targetPage = map.iconVisible();

			/*
			 * Data is valid = login success -test pass -logout Data is valid = login failed
			 * -test fail Data is invalid = login success -test fail -logout Data is invalid
			 * = login success -test pass
			 * 
			 */

			if (result.equals("valid")) {

				if (targetPage == true) {
					map.clickOnLogOut();
					Assert.assertTrue(true);

				} else {
				}
				Assert.assertTrue(false);
			}
			if (result.equals("invalid")) {

				if (targetPage == true) {
					map.clickOnLogOut();
					Assert.assertTrue(false);
				} else {
				}
				Assert.assertTrue(true);
			}
		} catch (Exception e) {
			Assert.fail();
		}

	}

}
