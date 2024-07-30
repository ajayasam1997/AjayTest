package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	// Constructor Extending from BasePage
	public HomePage(WebDriver driver) {
		super(driver);

	}

	// X- Paths

	@FindBy(xpath = "//a[normalize-space()='Log in']")
	@CacheLookup
	private WebElement logIn;
	@FindBy(xpath = "//a[normalize-space()='Register']")
	@CacheLookup
	private WebElement register;
	
	
	

	// Methods

	public void clickOnRegisterButton() {

		register.click();

	}
	
	public void ClickOnLoginButton() {
		
		logIn.click();
	}

}
