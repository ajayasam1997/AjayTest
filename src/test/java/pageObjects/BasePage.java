package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;



public class BasePage {

	/*
	 * Base Page consists only which is reusable in all page object classes
	 * 
	 */

	WebDriver driver;

	// Constructor
	public BasePage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

}
