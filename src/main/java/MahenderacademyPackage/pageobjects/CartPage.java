package MahenderacademyPackage.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import MahenderacademyPackage.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent
{		
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver); //sending driver object to parent class i.e. AbstractComponent class //this creates constructor in parent class i.e. AbstractComponent class
		this.driver =driver;
		PageFactory.initElements(driver, this); // to initialize page factory elements //to have a knowledge on driver for PageFactory i.e. @FindBy
	}
	
	
	@FindBy (css=".cartSection h3") 
	List <WebElement> cartProducts;
	
	@FindBy (css=".totalRow button") 
	WebElement checkoutEle;
	
	@FindBy (css=".cart") 
	WebElement cartTable;

	public Boolean VerifyProductDisplay(String productNeeded) //Action Method //here, productNeeded coming from test i.e. SubmitOderTest 
	{
//		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
//		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productNeeded));
		
		waitForWebElementToAppear(cartTable); // waiting for mycart table before iteration
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productNeeded));
		return match;
	}
	
	public CheckoutPage goToCheckout() 
	{
//		driver.findElement(By.cssSelector(".totalRow button")).click(); // click checkout button
		checkoutEle.click();
		return new CheckoutPage(driver);
		//use above single line OR use below two lines
//		CheckoutPage checkoutPage = new CheckoutPage(driver);
//		return checkoutPage;
		
	}
	

	

}
