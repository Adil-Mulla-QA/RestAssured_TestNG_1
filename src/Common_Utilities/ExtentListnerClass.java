package Common_Utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListnerClass implements ITestListener {
	
	ExtentSparkReporter sparkReporter;
	ExtentReports extentReport;
	ExtentTest test;
	
	public void reportConfigurations() {
				
		sparkReporter = new ExtentSparkReporter(".\\Extent-Report\\report.html");
		extentReport = new ExtentReports();
		
		extentReport.attachReporter(sparkReporter);
		
		//adding system/environment information to reports
		extentReport.setSystemInfo("OS", "Windows 11");
		extentReport.setSystemInfo("user", "AaDil");
		
		// Configuration for changing look & feel of report
		sparkReporter.config().setDocumentTitle("RestAssured Extent Listner Report");
		sparkReporter.config().setReportName("This is my First Extent-Report");
		sparkReporter.config().setTheme(Theme.DARK);
	}

//----> This Method will get Invoked before start of test case execution.(same as @BeforeClass Annotation i.e. Invokes Only Once)
	public void onStart(ITestContext result) {
		reportConfigurations();
		System.out.println("On Start Method Invoked...");
	}

//----> This Method will get Invoked before start of test case execution
	public void onFinish(ITestContext result) {		
		System.out.println("On Finished Method Invoked...");
		extentReport.flush();
	}

// ----> This Method will get Invoked whenever any test of the test case fails
	public void onTestFailure(ITestResult result) {
		System.out.println("Name of Test Method Failed : "+ result.getName());
		test = extentReport.createTest(result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed Test Case is : " + result.getName(), ExtentColor.RED));
	}

// ----> This Method will get Invoked whenever any test of the test case skipped
	public void onTestSkipped(ITestResult result) {
		System.out.println("Name of Test Skipped : "+ result.getName());
		test = extentReport.createTest(result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the Skipped Test Case is : " + result.getName(), ExtentColor.YELLOW));
	}

// ----> This Method will get Invoked on execution of each test case (same as @BeforeTest Annotation i.e 4 times in our project)
	public void onTestStart(ITestResult result) {
		System.out.println("Name of Test Method Started : "+ result.getName());
	}

// ----> This Method will get Invoked whenever any test of the test case Pass.
	public void onTestSuccess(ITestResult result) {
		System.out.println("Name of Test Method Executed Successfully : "+ result.getName());
		test = extentReport.createTest(result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the Passed Test Case is : " + result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailedButWithSuccessPercentage(ITestResult result) {
		// No Implementation of this method
	}
}
