package MahenderacademyPackage.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import MahenderacademyPackage.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	

//	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//	driver.close();
		
	
	WebDriver driver;
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy (css=".hero-primary") 
	WebElement confirmMessage;
	
	By confirmMessageText = By.cssSelector(".hero-primary");
	
	public String getConfirmationMessage ()
	{
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary"))); 
//		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		waitForElementToAppear(confirmMessageText);
		return confirmMessage.getText();
		
	}
	

}
