package MahenderacademyPackage.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

//import com.sun.net.httpserver.Authenticator.Retry;
import MahenderacademyPackage.TestComponents.BaseTest;
import MahenderacademyPackage.pageobjects.CartPage;
import MahenderacademyPackage.pageobjects.ProductCatalogue;
import MahenderacademyPackage.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest{

	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class) //if shows error, add import MahenderacademyPackage.TestComponents.Retry;
	public void LoginErrorValidation() throws IOException //, InterruptedException
	{	
		
//		String productNeeded= "ZARA COAT 3"; //this line not required b'coz we're not even going inside the application.
//		ProductCatalogue productCatalogue=landingPage.loginApplication("mahender@wrongEmail.com", "wrongPassword"); //giving wrong password and email intentionally //landingPage object coming from BeseTest class
		landingPage.loginApplication("mahender@wrongEmail.com", "wrongPassword"); //giving wrong password and email intentionally to validate error message //landingPage object coming from BeseTest class
//		landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage()); // <div role="alert" class="ng-tns-c4-9 toast-message ng-star-inserted" aria-label="Incorrect email or password." style=""> Incorrect email or password. </div>    //div[aria-label='Incorrect email or password.']		
	}
	
	@Test
	public void ProductErrorValidation()
	{	
		String productNeeded= "ZARA COAT 3";
		
		////LandingPage
//		driver.get("https://rahulshettyacademy.com/client");
//		LandingPage landingPage = new LandingPage(driver);  //to link this driver to constructor LandingPage in LandingPage class
//		landingPage.goTo();
//		LandingPage landingPage =launchApplication(); //method from BaseTest	//this line not required b'coz we're adding @BeforMethod on launchApplication method in BaseTest
	
//		ProductCatalogue productCatalogue=landingPage.loginApplication("mahender2@mahender2.com", "Mahender1@123"); //here, linking next page and login with credentials. //landingPage object coming from BaseTest
		ProductCatalogue productCatalogue=landingPage.loginApplication("mahender@mahender.com", "Mahender@123"); //here, linking next page and login with credentials. //landingPage object coming from BaseTest

		
		////ProductCatalogue		
//		ProductCatalogue productCatalogue = new ProductCatalogue(driver); //creating object like this for every class i.e. page object classes is overhead, so, write this in previous page object class 
		List <WebElement> Products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productNeeded);	
		
		CartPage cartPage = productCatalogue.goToCartPage();
		
		////CartPage
//		CartPage cartPage = new CartPage(driver);			
		Boolean match3 = cartPage.VerifyProductDisplay("ZARA COAT 33"); ////giving product name intentionally //landingPage object coming from BeseTest class
		Assert.assertFalse(match3); //change to assertFalse b'coz from above we'll get false, hence, this test will pass
	
	}
}
