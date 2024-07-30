package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	// This is a utility file; it is same through out the project
	// no need of remembering this, if freshers can understand than its ok

	public ExtentSparkReporter sparkReporter; // Creates the UI of the report
	public ExtentReports extent; // populate common info on the report
	public ExtentTest test; // creating test case entries in the report and update status of the test
							// methods

	String repName;

	public void onStart(ITestContext testContext) {

		// We can create time Stamp using SimpleDateFormat class and Date Class
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.ss");// date format

		Date dt = new Date(); // date

		String currentDateTimeStamp = df.format(dt); // currentDateTimeStamp

		repName = "Test-report" + currentDateTimeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // Specify location

		sparkReporter.config().setDocumentTitle("hdf project Automation Report"); // Title of the report
		sparkReporter.config().setReportName("hdf functional testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();

		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Hdf");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("SubModule", "Customers");
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA"); // All the values we can dynamically but fixed for now in all reports

		String os = testContext.getCurrentXmlTest().getParameter("os");// gives the os information from
		// xml


		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");// gives the browser information from
																					// xml

		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();

		if (!includedGroups.isEmpty()) {

		}
		extent.setSystemInfo("Groups", includedGroups.toString()); // Displays the groups which are included while
																	// grouping

	}

	// On start Method starts before starting all the test, empty reports will be
	// there

	public void OnTestSuccess(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName()); // create new entry in the report
		test.assignCategory(result.getMethod().getGroups()); // To display groups in reports
		test.log(Status.PASS, result.getName() + " got passed"); // Update the status

	}

	public void OnTestFailure(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName()); // create new entry in the report
		test.assignCategory(result.getMethod().getGroups()); // To display groups in reports
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage()); // returns the reason

		try {// Capture Screenshots

			String imgpath = new BaseClass().captureScreen(result.getName());

			test.addScreenCaptureFromPath(imgpath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void OnTestSkipped(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName()); // create new entry in the report
		test.assignCategory(result.getMethod().getGroups()); // To display groups in reports
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage()); // returns the reason

	}

	public void OnTestFinish(ITestResult context) {

		extent.flush();

		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\"+repName;

		File extentReport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
