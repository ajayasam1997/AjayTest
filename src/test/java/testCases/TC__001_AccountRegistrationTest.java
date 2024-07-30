package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC__001_AccountRegistrationTest extends BaseClass {

	// Here in the TestCase class We call all methods by creating object from the
	// PageObjects Class

	String password = randomAlphaNumeric();

	@Test(groups = { "master", "sanity" })
	public void accountVerify() {

		logger.info("*******Account Registration started*********");

		logger.debug("Debug logging started i.e Application logs");

		try {

			// Objects creation for HomePage and RegistrationPage Object Classes
			RegistrationPage registrationPage = new RegistrationPage(driver);
			HomePage homePage = new HomePage(driver);

			// HomePage methods called
			homePage.clickOnRegisterButton();

			logger.info("**********clicked on register button Passed***********");
			// RegistrationPage methods called
			registrationPage.clickOnGender();
			logger.info("**********gender Passed***********");
			registrationPage.setFirstName(randomAlphabetic().toUpperCase());
			logger.info("**********firstName Passed***********");
			registrationPage.setlastName(randomAlphabetic().toUpperCase());
			logger.info("**********lastName Passed***********");
			registrationPage.dayDropdown();
			logger.info("**********day Passed***********");
			registrationPage.monthDropdown();
			logger.info("**********month Passed***********");
			registrationPage.yearDropdown();
			logger.info("**********year Passed***********");
			registrationPage.setEmail(randomAlphaNumeric().toLowerCase());
			logger.info("**********Email written***********");

			registrationPage.clickOnNewsLetter();
			logger.info("**********clicked on newsletter***********");
			registrationPage.setPassword(password);
			logger.info("**********entered  Password***********");
			registrationPage.confirmPassword(password);
			logger.info("**********passsword confirmed***********");
			registrationPage.clickOnRegister();
			logger.info("**********clicked on register button***********");
			String msg = registrationPage.registrationCompletedMessage();
			if (msg.equals("Your registration completed")) {
				
				Assert.assertTrue(true);

				logger.info("**********Registration completed ***********");
				Assert.assertEquals(msg, "Your registration completed");

				logger.info("============TC_001 Account RegsitrationTest done============");

			} else {
				logger.error("**********Registration failed ***********");
				Assert.assertTrue(false);
				
				
			}

		} catch (Exception e) {
			
			
			logger.error("logger.info(\"============TC_001 Account RegsitrationTest failed============\");");
		    logger.debug("Registration failed");
		    Assert.fail();
		}
logger.info("******Finished TC 001 Account Registration test******");
	}
    
}
