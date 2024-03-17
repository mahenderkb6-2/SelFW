package MahenderacademyPackage.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import MahenderacademyPackage.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent
{		
	WebDriver driver;
	public OrdersPage(WebDriver driver)
	{
		super(driver); //sending driver object to parent class i.e. AbstractComponent class //this creates constructor in parent class i.e. AbstractComponent class
		this.driver =driver;
		PageFactory.initElements(driver, this); // to initialize page factory elements //to have a knowledge on driver for PageFactory i.e. @FindBy
	}
	
	
	
	@FindBy (css="tr td:nth-child(3)") 
	List <WebElement> productNames;

	
	@FindBy(css="table")	//table table-bordered table-hover ng-star-inserted
	WebElement orderPageTableLoad;									
		
//	@FindBy(css="button[routerlink='/dashboard']")		//button routerlink="/dashboard" 
//	WebElement goBacktoShopButton;				

	public Boolean VerifyOrderDisplay(String productNeeded) //Action Method //here, productNeeded coming from test i.e. SubmitOderTest 
	{
//		List <WebElement> productNames = driver.findElements(By.cssSelector("tr td:nth-child(3)"));	
//		Boolean match = productNames.stream().anyMatch(productName -> productName.getText().equalsIgnoreCase(productNeeded));
		
		waitForWebElementToAppear(orderPageTableLoad);	//to load the table in orders page before iteration
//		waitForWebElementToAppear(goBacktoShopButton);	//to load the goBacktoShop Button before iteration
		Boolean match2 = productNames.stream().anyMatch(productName -> productName.getText().equalsIgnoreCase(productNeeded));
		return match2;
	}
	
}
