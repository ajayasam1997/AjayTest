package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//a[normalize-space()='Log out']")
	@CacheLookup
	private WebElement logOut;

	@FindBy(xpath = "//img[@alt='nopCommerce demo store']")
	@CacheLookup
	private WebElement nopCommerceDemoStore;

	// Action Method

	public void clickOnLogOut() {

		logOut.click();
	}
	
	public boolean iconVisible() {
		
		
		return nopCommerceDemoStore.isDisplayed();

		
	}


}
