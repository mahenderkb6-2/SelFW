package MahenderacademyPackage.tests;
import java.time.Duration;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
//		driver.manage(). timeouts ().implicitlyWait(Duration.ofSeconds(10)); 
		String productNeeded= "ZARA COAT 3";
		
		//LandingPage
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("mahender@mahender.com");
		driver.findElement(By.id("userPassword")).sendKeys("Mahender@123");
		driver.findElement(By.id("login")).click();
		
		//ProductCatalogue
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector (".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector (".mb-3"));
		WebElement prod = products.stream().filter(product ->
			product.findElement(By.cssSelector("b")).getText().equals(productNeeded)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container"))); //product added to cart notification at bottom-right
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container"))); // this line is taking time so, using below line
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container")))); 
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click(); 
		
		//CartPage
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
//		WebElement cartProd = cartProducts.stream().filter(cartProduct -> cartProduct.findElement(By.cssSelector("div.cartSection h3")).getText().equals("ZARA COAT 3"));
//		cartProducts.stream().filter(cartProduct -> cartProduct.getText().equalsIgnoreCase(productNeeded));
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productNeeded));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//CheckoutPage
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform(); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click(); 
		
		//ConfirmationPage
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary"))); 
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//		driver.close();
		
		//OrdersPage
		driver.findElement(By.cssSelector("button[routerlink*='/dashboard/myorders']")).click();
		List <WebElement> productNames = driver.findElements(By.cssSelector("tr td:nth-child(3)"));
		Boolean match2 = productNames.stream().anyMatch(productName -> productName.getText().equalsIgnoreCase(productNeeded));
		Assert.assertTrue(match2);
//		System.out.println(match2);
		driver.close();


	}

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

