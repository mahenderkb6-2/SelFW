package MahenderacademyPackage.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MahenderacademyPackage.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver); //sending driver object to parent class i.e. AbstractComponent class //this creates constructor in parent class i.e. AbstractComponent class
		this.driver =driver;
		PageFactory.initElements(driver, this); // to initialize page factory elements //to have a knowledge on driver for PageFactory i.e. @FindBy
		
	}

	//PageFactory
	@FindBy (id="userEmail") 
	WebElement userEmail;
	
	@FindBy (id="userPassword") 
	WebElement PasswordEle;
	
	@FindBy (id="login") 
	WebElement submit; //// div.ng-tns-c4-13.toast-message.ng-star-inserted
	
//	@FindBy (css=".ng-tns-c4-13.toast-message.ng-star-inserted") //// <div role="alert" class="ng-tns-c4-9 toast-message ng-star-inserted" aria-label="Incorrect email or password." style=""> Incorrect email or password. </div>
//	@FindBy (css=".ng-tns-c4-9.toast-message.ng-star-inserted") //.ng-tns-c4-9.toast-message.ng-star-inserted
	@FindBy (css="*.toast-message.ng-star-inserted") //Or @FindBy (css="div[class*='toast-message ng-star-inserted']")
	WebElement errorMessage;
	
	
	
	public ProductCatalogue loginApplication(String email, String passowrd)	//Action Method //here, email and password coming from test i.e. SubmitOderTest 
	{
//		driver.findElement(By.id("userEmail")).sendKeys("mahender@mahender.com");
//		driver.findElement(By.id("userPassword")).sendKeys("Mahender@123");
//		driver.findElement(By.id("login")).click();
		userEmail.sendKeys(email);
		PasswordEle.sendKeys(passowrd);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver); //linking next page i.e ProductCatalogue page to LandingPage
		return productCatalogue;
	}
	
	public void goTo()	//Action Method
	{
//		driver.get(url);
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
