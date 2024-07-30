package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	// Here in the TestCase class We call all methods by creating object from the
	// PageObjects Class

	@Test(groups = { "sanity" })
	public void verifyLogin() {
		
		logger.info("*********Starting loginTest*******");
try {
		// Creating objects for HomePage and LoginPage

		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);

		
			hp.ClickOnLoginButton();
			logger.info("clicked on login button in homePage");
			lp.enterEmailId(p.getProperty("emailId"));
			logger.info("emailId entered");
			lp.enterLoginPassWord(p.getProperty("password"));
			logger.info("password entered");
			lp.clickOnRememberMeCheckBox();
			logger.info("Check box clicked");
			lp.clickOnLOgInButton();
			logger.info("Clicked on Login Button");
			
			//MyAccountPAge
			
			MyAccountPage mp = new MyAccountPage(driver);
			boolean targetPage=mp.iconVisible();
			Assert.assertTrue(targetPage);
			
}
catch(Exception e) {

	Assert.fail();
}
		
		
	}

}
