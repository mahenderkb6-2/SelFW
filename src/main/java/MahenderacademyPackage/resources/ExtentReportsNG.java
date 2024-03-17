package MahenderacademyPackage.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	
	public static ExtentReports getReportToObject() //adding static for accessing this method without declaring object
	{
		//ExtentReports and ExtentSparkReporter classes
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); //calling ExtentSparkReporter class is from ExtentReports dependency
		reporter.config().setReportName("Web Automation Results"); //In website i.e. at top-right
		reporter.config().setDocumentTitle("Test Results"); //i.e. tab title
		
		ExtentReports extent = new ExtentReports();	//calling main class i.e. ExtentReports class is from ExtentReports dependency
		extent.attachReporter(reporter); // attaching main class i.e. ExtentSparkReporter and ExtentReports //reporter have all the metadata
		extent.setSystemInfo("Tester", "MahenderTester");
		return extent;
		
		
	}

}
