package MahenderacademyPackage.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import MahenderacademyPackage.TestComponents.BaseTest;
import MahenderacademyPackage.pageobjects.CartPage;
import MahenderacademyPackage.pageobjects.CheckoutPage;
import MahenderacademyPackage.pageobjects.ConfirmationPage;
import MahenderacademyPackage.pageobjects.OrdersPage;
import MahenderacademyPackage.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{

//	public static void main(String[] args) { //static is not required, we can use TestNG annotations
	
//	String productNeeded= "ZARA COAT 3"; //this is accessible for all methods //this line not required b'coz we're getting it from @DataProvider
	
	@Test(dataProvider="getData", groups= {"Purchase"})
//	public void submitOrderTest(String email, String password, String productNeeded) throws IOException //(String email, String password, String productNeeded) is from @DataProvider
	public void submitOrderTest(HashMap <String, String> input) throws IOException //for HashMap DataProvider
	{

//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();

		
		////LandingPage
//		driver.get("https://rahulshettyacademy.com/client");
//		LandingPage landingPage = new LandingPage(driver);  //to link this driver to constructor LandingPage in LandingPage class
//		landingPage.goTo();
//		LandingPage landingPage =launchApplication(); //method from BaseTest	//this line not required b'coz we're adding @BeforMethod on launchApplication method in BaseTest
	
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password")); //here, linking next page and login with credentials. //landingPage object coming from BaseTest
		
		////ProductCatalogue		
//		ProductCatalogue productCatalogue = new ProductCatalogue(driver); //creating object like this for every class i.e. page object classes is overhead, so, write this in previous page object class 
		List <WebElement> Products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productNeeded"));	
		
		CartPage cartPage = productCatalogue.goToCartPage();
		
		////CartPage
//		CartPage cartPage = new CartPage(driver);			
		Boolean match = cartPage.VerifyProductDisplay(input.get("productNeeded")); 
		System.out.println("***********"+match+"***********");
		Assert.assertTrue(match);
		
		
		CheckoutPage checkoutPage = cartPage.goToCheckout();		
		
		////CheckoutPage
//		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.selectCountry("india");
	
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		////ConfirmationPage
//		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//		driver.close();  	//this line not required b'coz we're adding @AfterMethod on tearDown method in BaseTest

	}
	
	@Test (dataProvider="getData", dependsOnMethods = {"submitOrderTest"}) //this will'run only after submitOrderTest i.e above test
//	public void OrderHistory(String email, String password, String productNeeded) throws InterruptedException //(String email, String password, String productNeeded) is from @DataProvider
	public void OrderHistory(HashMap <String, String> input) throws InterruptedException //for HashMap DataProvider
	{
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));		
		
		OrdersPage ordersPage= productCatalogue.goToOrdersPage(); 
		
		//OdersPage
		Boolean match2 = ordersPage.VerifyOrderDisplay(input.get("productNeeded"));
		System.out.println("***////***"+match2+"***////***");
		Assert.assertTrue(match2);
	}
	
	
	//Json HashMap DataProvider
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String, String>> data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\MahenderacademyPackage\\data\\PurchaseOrder.json");
		return new Object [][] {{data.get(0)}, {data.get(1)}};	//{data.get(0)} means 1st data set in json file	
	}
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object [][] {{"mahender@mahender.com", "Mahender@123", "ZARA COAT 3"}, {"mahender2@mahender2.com", "Mahender1@123", "ADIDAS ORIGINAL"}}; //here, {"mahender@mahender.com", "Mahender@123"} = one data set
//		
//	}
	
//	//HashMap DataProvider
//	@DataProvider
//	public Object[][] getData()
//	{
//		HashMap <String, String> map = new HashMap<String, String>();
//		map.put("email", "mahender@mahender.com");
//		map.put("password", "Mahender@123");
//		map.put("productNeeded", "ZARA COAT 3");
//		
//		HashMap <String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "mahender2@mahender2.com");
//		map2.put("password", "Mahender1@123");
//		map2.put("productNeeded", "ADIDAS ORIGINAL");
//		
//		return new Object [][] {{map}, {map2}};		
//	}

}
//WebElement prod = products.stream().filter(product->
//product.findElement(By.cssSelector("b")).getText().equals(productNeeded)).findFirst().orElse(null);
//prod. findElement (By.cssSelector(".card-body button:last-of-type")).click();
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
////ng-animating 
//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
//
//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

//List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
//Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productNeeded));
//Assert.assertTrue(match);
//driver.findElement(By.cssSelector(".totalRow button")).click();
////Actions a = new Actions (driver);
////a.sendKeys (driver.findElement(By.cssSelector("[placeholder»'Select Country']")), "india").build().perform();

