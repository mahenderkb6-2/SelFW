package MahenderacademyPackage.pageobjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import MahenderacademyPackage.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver); //sending driver object to parent class i.e. AbstractComponent class //this creates constructor in parent class i.e. AbstractComponent class
		this.driver =driver;
		PageFactory.initElements(driver, this); // to initialize page factory elements //to have a knowledge on driver for PageFactory i.e. @FindBy
	}
	
//	driver.findElement(By.cssSelector("button[routerlink*='cart']")).click(); 


	//PageFactory
	@FindBy (css=".mb-3") 
	List <WebElement> products;
	
	@FindBy (id="toast-container") 
	WebElement spinner;
	
	List <WebElement> Products;
	By productsBy = By.cssSelector (".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");

	
	public List<WebElement> getProductList()	//Action Method //here, email and password coming from test i.e. SubmitOderTest 
	{
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector (".mb-3")));
//		List<WebElement> products = driver.findElements(By.cssSelector (".mb-3"));
		waitForElementToAppear(productsBy); //method is coming from parent class i.e. AbstractComponent class 
		return products;

	}
	
	public WebElement getProductsByNAme(String productNeeded) //Action Method //here, productNeeded coming from test i.e. SubmitOderTest 
	{
//		WebElement prod = products.stream().filter(product ->
//		product.findElement(By.cssSelector("b")).getText().equals(productNeeded)).findFirst().orElse(null);
		WebElement prod = getProductList().stream().filter(product ->
		product.findElement(By.cssSelector("b")).getText().equals(productNeeded)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productNeeded) //this depends on getProductsByNAme method which again depends on getProductList method
	{
//		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container"))); //product added to cart notification at bottom-right
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container")))); 
		WebElement prod = getProductsByNAme(productNeeded);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		
	}


}
