package MahenderacademyPackage.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import MahenderacademyPackage.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
//	WebDriverManager.chromedriver().setup();
//	WebDriver driver = new ChromeDriver();
//	driver.manage().window().maximize();
//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

	
	public WebDriver driver; // driver coming from below loop. i.e. if it is Chrome then it comes only from Chrome block.
	public LandingPage landingPage = new LandingPage(driver); 
	
	public WebDriver initializeDriver() throws IOException
	{
		//Properties class
		Properties prop = new Properties(); //this class gets our GlobalData file
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\MahenderacademyPackage\\resources\\GlobalData.properties"); //this class converts our GlobalData file into InputStream object
		//System.getProperty("user.dir") = this will get the project path automatically i.e. C:\Users\mahendra\selenium training2\SeleniumFrameworkDesign
		prop.load(fis); //expects InputStream
		
		
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") :prop.getProperty("browser"); //if no value in maven command then it follows value in GlobalData.properties file 	//this line is to set global values from maven commands
		
//		String browserName =prop.getProperty("browser"); // calling attribute in our global property file 	//this line is not required if the above line is String browserName = System.getProperty("browser") != null ? System.getProperty("browser") :prop.getProperty("browser");
		
		
		
//		if(browserName.equalsIgnoreCase("Chrome"))
		if(browserName.contains("chrome")) //for headless chrome
		{
			
//			//Headless chrome -part1
			ChromeOptions options = new ChromeOptions();
								
			//Chrome
			WebDriverManager.chromedriver().setup(); //webdrivermanager is a dependency
			//OR
			//System.setProperty("webdriver.chrome.driver", "C:\\Users\\mahendra\\OneDrive\\Desktop\\all2\\selenium course2\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
//			driver = new ChromeDriver(); 
			
			//Headless chrome -part2
			if(browserName.contains("headless")) //for headless chrome
			{
				options.addArguments("headless");	
			}	
			driver = new ChromeDriver(options); // options object is for headless chrome
			driver.manage().window().maximize();	//for headless chrome, to identify all elements in FULL SCREEN mode	//driver.manage().window().setSize(new Dimension(1400,900));
		}
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			//Firefox
			WebDriverManager.firefoxdriver().setup(); 
			driver = new FirefoxDriver();
			
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			//Edge
			WebDriverManager.edgedriver().setup(); 
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize(); //this is outside b'coz it is common for all browsers
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver(); //calling above initializeDriver method
//		LandingPage landingPage = new LandingPage(driver); //this line not required b'coz we're adding this object globally i.e. above the methods
		landingPage = new LandingPage(driver); //landingPage object coming from global object i.e. above the methods which is calling goTo() method in LandingPage class
		landingPage.goTo();
		return landingPage;	
	}
	
	//close the window
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
	//json file conversation
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException //filePath coming from test
	{
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);//StandardCharsets.UTF_8 = is encoding on how to convert into string
		
		//string to HashMap  conversion using dependency "Jackson Databind" in the form of List. List form b'cpz we have two HashMaps i.e. two index (i.e. two data sets)
		ObjectMapper mapper = new ObjectMapper(); //ObjectMapper is from "Jackson Databind" dependency
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
		
		//{map}, {map2}
		
	}
	
	//Screenshot
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException //driver coming from Listeners 
	{
		TakesScreenshot ts = (TakesScreenshot)driver; //b'coz we're getting Screenshot from driver //TakesScreenshot class from selenium
		File sourceFile = ts.getScreenshotAs(OutputType.FILE); //here, we're taking Screenshot
		File destFile = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(sourceFile, destFile); //copying Screenshot file from sourceFile location and pasting in destFile location
		//return destFile; //Or use below line
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
	}

}
