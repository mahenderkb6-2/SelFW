package MahenderacademyPackage.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MahenderacademyPackage.pageobjects.CartPage;
import MahenderacademyPackage.pageobjects.OrdersPage;

public class AbstractComponent {
	
//	WebDriver driver;
//	public AbstractComponent(WebDriver driver)
//	{
//		this.driver=driver;
//	}
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) { //driver coming from child class "LandingPage"
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	@FindBy(css="button[routerlink*='cart']") //or @FindBy(css="button[routerlink*='/dashboard/cart']")
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
//	@FindBy(css="button[routerlink='/dashboard/myorders']") 
//	@FindBy(css="li button[routerlink='/dashboard/myorders']") 
	@FindBy(css="nav ul li button[routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	// 

	public void waitForElementToAppear(By findBy) //here, findBy coming from test i.e. SubmitOderTest 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele) //here, findBy coming from test i.e. SubmitOderTest 
	{	
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container")))); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));	
		wait.until(ExpectedConditions.invisibilityOf(ele)); 
	}
	
	public CartPage goToCartPage() //here, findBy coming from test i.e. SubmitOderTest 
	{
//		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click(); 
		
		waitForWebElementToAppear(cartHeader); //waiting for cart button before clicking
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);	
		return cartPage;
	}
	
	public void waitForWebElementToAppear(WebElement vo) //here, findBy coming from test i.e. SubmitOderTest 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(vo));
	}
	
	public OrdersPage goToOrdersPage() throws InterruptedException 
	{
//		driver.findElement(By.cssSelector("button[routerlink*='/dashboard/myorders']")).click();
		waitForWebElementToAppear(orderHeader); //waiting for orders button
		orderHeader.click();
		OrdersPage ordersPage = new OrdersPage(driver);	
		return ordersPage;
	}

}
