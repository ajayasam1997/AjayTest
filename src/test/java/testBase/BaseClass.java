package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;   //Log4j
import org.apache.logging.log4j.Logger;  //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger; // Logger is the interface
	public Properties p;

	// All the reusable methods which we use for each testcase can be kept in
	// BaseClass,
	// Afterwards we can extend to each testcase classes when required

	@Parameters({ "os", "browser" })
	@BeforeClass(groups = { "master", "sanity" })
	public void setUp(String os, String br) throws Exception {

		// for loading properties file

		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		//loading the properties file
		p.load(file);

		//// for loading logjFile
		logger = LogManager.getLogger(this.getClass()); // log4j

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();

			// Operating System

			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}

			else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}

			else {
				System.out.println("No os matching");
				return;
			}

			// Launching browser based on conditions

			switch (br.toLowerCase()) {

			case "chrome":
				capabilities.setBrowserName("chrome");
				break;

			case "edge":
				capabilities.setBrowserName("chrome");
				break;

			default:
				System.out.println("No matching browser...");
				return;
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		} else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			// Switching browser locally on condition

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();

				break;

			case "edge":
				driver = new EdgeDriver();

				break;

			default:
				System.out.println("No matching browser...");
				return;
			}
		}

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to(p.getProperty("appWebsite"));// reading url from properties file
		driver.manage().window().maximize();

	}
	@Parameters({ "os", "browser" })
	@AfterClass(groups = { "master", "sanity" })
	public void tearDown() {
		driver.quit();

	}

	// Random Generate of the Strings at runTime(thirdparty dependency) lang3)

	public String randomAlphabetic() {

		String generatedAlphabetic = RandomStringUtils.randomAlphabetic(5);
		return generatedAlphabetic;
	}

	public String randomNumeric() {

		String generatedNumeric = RandomStringUtils.randomNumeric(5);
		return generatedNumeric;
	}

	public String randomAlphaNumeric() {
		String alphabetic = RandomStringUtils.randomAlphabetic(8);
		String numeric = RandomStringUtils.randomNumeric(4);
		String alphaNumeric = alphabetic + "" + numeric + "@gmail.com";
		return alphaNumeric;
	}

	public String captureScreen(String tName) throws IOException{

		String timeStamp = new SimpleDateFormat("yyyyMMddHHss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty(("user.dir")+"\\screenshots\\" + tName + "_" + timeStamp);
		File targetFile = new File(targetFilePath);
		return targetFilePath;
	}

//public class ScreenshotUtil {
//    WebDriver driver;
//
//    // Constructor to initialize WebDriver
//    public ScreenshotUtil(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    public String captureScreen(String tName) {
//        // Create timestamp
//        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        
//        // Take screenshot and store as a file format
//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
//        
//        // Define the target file path
//        String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tName + "_" + timeStamp + ".png";
//        File targetFile = new File(targetFilePath);
//        
//        // Copy the screenshot to the desired location
//        try {
//            FileUtils.copyFile(sourceFile, targetFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        
//        return targetFilePath;
//    }

//public String captureScreen(String tName) {
//    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//    File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
//    String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + tName + "_" + timeStamp + ".png";
//    File targetFile = new File(targetFilePath);
//    try {
//        FileUtils.copyFile(srcFile, targetFile);
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//    return targetFilePath;
//}
}
