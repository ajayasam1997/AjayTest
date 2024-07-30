package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	// Constructor extended from BasePage
	public LoginPage(WebDriver driver) {
		super(driver);

	}

	// X-Paths

	@FindBy(xpath = "//input[@id='Email']")
	@CacheLookup
	private WebElement loginEmail;
	@FindBy(xpath = "//input[@id='Password']")
	@CacheLookup
	private WebElement loginPassword;
	@FindBy(xpath = "//input[@id='RememberMe']")
	@CacheLookup
	private WebElement rememberMeCheckBox;
	@FindBy(xpath = "//button[normalize-space()='Log in']")
	@CacheLookup
	private WebElement logInButton;

	// Creating Action Methods

	public void enterEmailId(String email) {
		loginEmail.sendKeys(email);

	}

	public void enterLoginPassWord(String passWord) {

		loginPassword.sendKeys(passWord);
	}

	public void clickOnRememberMeCheckBox() {
		rememberMeCheckBox.click();

	}

	public void clickOnLOgInButton() {
		logInButton.click();

	}

}
