package MahenderacademyPackage.TestComponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import MahenderacademyPackage.resources.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener{ // inherits BaseTest for getScreenshot method
	
	ExtentTest test;
	ExtentReports extent = ExtentReportsNG.getReportToObject(); // calling getReportToObject method in ExtentReportsNG class
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal(); //Thread safe for "test" object to avoid concurrency 
	
	@Override
	public void onTestStart(ITestResult result) { //before executing any test, it'll execute this method
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		test = extent.createTest(result.getMethod().getMethodName()); //test object will have an entry into our report about our test case.	 //getMethodName means the method name that we're running for test
		extentTest.set(test); //unique thread id(ErrorValidationsTest) -->test		// adding "test" object to Thread safe to avoid concurrency by assigning unique thread of ErrorValidationsTest
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
//		test.log(Status.PASS, "Test Passed"); //not necessary b'coz by default it comes to this method when test passed
		
		//Screenshot even test is passed
		try { // the line, driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()); , , converted to below try catch automatically based on suggestion //// if this gets error "WebDriver cannot be resolved to a type" then add "import org.openqa.selenium.WebDriver;" at top
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()); 
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null; // the line, String filePath = getScreenshot(result.getMethod().getMethodName()); , converted to below try catch automatically based on suggestion
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver); // calling getReportToObject method in ExtentReportsNG class by inheriting with BaseTest
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()); //here, result.getMethod().getMethodName() is for getting the title
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		
		
		try { // the line, driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()); , , converted to below try catch automatically based on suggestion //// if this gets error "WebDriver cannot be resolved to a type" then add "import org.openqa.selenium.WebDriver;" at top
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()); 
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//OR use below try catch block
//		try {
//			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
//					.get(result.getInstance()); //getTestClass() is from <listener class-name="MahenderacademyPackage.TestComponents.Listeners"> in testng.xml file.
//			
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
//		test.log(Status.FAIL, "Test Failed");	// This will fail the test and shows "Test Failed"
		extentTest.get().fail(result.getThrowable()); // This will print error message when test fails
		
		//step1: Screenshot, step2: Attach Report
		String filePath = null; // the line, String filePath = getScreenshot(result.getMethod().getMethodName()); , converted to below try catch automatically based on suggestion
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver); // calling getReportToObject method in ExtentReportsNG class by inheriting with BaseTest
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()); //here, result.getMethod().getMethodName() is for getting the title
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		extent.flush();
	}
	
	

}
