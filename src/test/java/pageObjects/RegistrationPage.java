package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BasePage {

	// Constructor extended from BasePage
	public RegistrationPage(WebDriver driver) {
		super(driver);

	}

	// All the X-paths of the Elements

	@FindBy(xpath = "//input[@id='FirstName']")
	@CacheLookup
	private WebElement firstName;
	@FindBy(xpath = "//input[@id='gender-male']")
	@CacheLookup
	private WebElement genderMale;
	@FindBy(xpath = "//input[@id='LastName']")
	@CacheLookup
	private WebElement lastName;
	@FindBy(xpath = "//select[@name='DateOfBirthDay']")
	@CacheLookup
	private WebElement dayElement;
	@FindBy(xpath = "//select[@name='DateOfBirthMonth']")
	@CacheLookup
	private WebElement monthElement;
	@FindBy(xpath = "//select[@name='DateOfBirthYear']")
	@CacheLookup
	private WebElement yearElement;
	@FindBy(xpath = "//input[@id='Email']")
	@CacheLookup
	private WebElement email;
	@FindBy(xpath = "//input[@id='Newsletter']")
	@CacheLookup
	private WebElement newsletter;
	@FindBy(xpath = "//input[@id='Password']")
	@CacheLookup
	private WebElement password;
	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	@CacheLookup
	private WebElement confirmPassword;

	@FindBy(xpath = "//button[@id='register-button']")

	@CacheLookup
	private WebElement register;
	@FindBy(xpath = "//div[@class='result']")
	@CacheLookup
	private WebElement yourRegistrationCompleted;

	// Creating Action methods for the Elements

	public void setFirstName(String fname) {
		firstName.sendKeys(fname);
	}

	public void setlastName(String lname) {
		lastName.sendKeys(lname);
	}

	public void clickOnGender() {
		genderMale.click();
		;
	}

	public void dayDropdown() {
		Select dayDropdown = new Select(dayElement);

		dayDropdown.selectByValue("11");

	}

	public void monthDropdown() {
		Select monthDropdown = new Select(monthElement);

		monthDropdown.selectByValue("4");

	}

	public void yearDropdown() {
		Select yearDropdown = new Select(yearElement);

		yearDropdown.selectByValue("1997");

	}

	public void setEmail(String mailId) {
		email.sendKeys(mailId);
	}

	public void setPassword(String passwordId) {
		password.sendKeys(passwordId);
	}

	public void confirmPassword(String confirmPasswordId) {
		confirmPassword.sendKeys(confirmPasswordId);
		
	}

	public void clickOnNewsLetter() {
		newsletter.click();
	}

	public void clickOnRegister() {
		register.click();
	}

	public String registrationCompletedMessage() {
		String text = yourRegistrationCompleted.getText();
		return text;

	}
}
