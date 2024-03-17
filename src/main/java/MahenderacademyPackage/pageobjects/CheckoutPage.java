package MahenderacademyPackage.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MahenderacademyPackage.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy (css="input[placeholder='Select Country']")
	WebElement country;
	@FindBy (xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	@FindBy (css=".action__submit")
	WebElement submit;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform(); //countryName coming from test
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		waitForElementToAppear(results);
//		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		selectCountry.click();
	}
	
	
	public ConfirmationPage submitOrder()
	{
//		driver.findElement(By.cssSelector(".action__submit")).click(); 
		submit.click();
		return new ConfirmationPage(driver);
	}

}
