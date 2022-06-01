package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import udacity.project2.globalVariables;

public class CartPage {
	
	WebDriver driver ;
	
	private WebElement addToCartButton() {return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div[2]/div[3]/div[2]/button[1]"));}
	private WebElement messageBar() {return driver.findElement(By.xpath("/html/body/div[5]/div/p"));}
	private WebElement addToWishlistButton() {return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div[2]/div[3]/div[2]/button[3]"));}
	private WebElement addToCompareListButton() {return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div[2]/div[3]/div[2]/button[2]"));}
	private WebElement cartButton() {return driver.findElement(By.xpath("//*[@id=\"topcartlink\"]"));}
	private WebElement termsOfServiceCheckBox() {return driver.findElement(By.xpath("//*[@id=\"termsofservice\"]"));}
	private WebElement checkoutButton() {return driver.findElement(By.xpath("//*[@id=\"checkout\"]"));}
	private WebElement countryTextBox() {return driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_CountryId\"]"));}
	private WebElement cityTextBox(){return driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_City\"]"));}
	private WebElement address1TextBox() {return driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_Address1\"]"));}
	private WebElement zipCodeTextBox() {return driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_ZipPostalCode\"]"));}
	private WebElement phoneTextBox() {return driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_PhoneNumber\"]"));}
	private WebElement placeOrderButton () {return driver.findElement(By.xpath( "/html/body/div[6]/div[3]/div/div/div/div[2]/ol/li[6]/div[2]/div[2]/button"));}
	private WebElement orderMessage () {return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div/div[2]/div[2]/a"));}
	private WebElement bookCategory() {return driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[5]"));}
	private WebElement totalLabel() {return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/ol/li[6]/div[2]/div[1]/div/div/div/form/div[1]/table/tbody/tr/td[2]/a/img"));}
	
	public CartPage (WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		
	}
	
	public CartPage (WebDriver driver, boolean navigateToHome) throws InterruptedException {
		
		this.driver = driver;
		this.driver.navigate().to("https://demo.nopcommerce.com");
		Thread.sleep(2000);
		PageFactory.initElements(this.driver, this);
		
	}
	
	
	public void addToCart () throws InterruptedException {
		
		addToCartButton().click();
		Thread.sleep(2000);
		
	}
	
	public void addToWishlist () throws InterruptedException {
		
		addToWishlistButton().click();
		Thread.sleep(1000);
		
	}
	
	public void addToComparList () throws InterruptedException {
		
		addToCompareListButton().click();
		Thread.sleep(1000);
		
	}
	
	public void createOrder() throws InterruptedException {
		
		Thread.sleep(3000);
		cartButton().click();
		
    	Thread.sleep(3000);
		termsOfServiceCheckBox().click();
		checkoutButton().click();
    	
    	// Set country, city and address
    	countryTextBox().sendKeys("Egypt");
    	cityTextBox().sendKeys("Cairo");
    	address1TextBox().sendKeys("Tahanoub");
    	
    	// Set zip code and phone
    	zipCodeTextBox().sendKeys("80000");
    	phoneTextBox().sendKeys("258280000");
    	
    	String [] continueButtonXpathsList= {"/html/body/div[6]/div[3]/div/div/div/div[2]/ol/li[1]/div[2]/div/button[4]",
    			                   "/html/body/div[6]/div[3]/div/div/div/div[2]/ol/li[3]/div[2]/form/div[2]/button",
    			                   "/html/body/div[6]/div[3]/div/div/div/div[2]/ol/li[4]/div[2]/div/button",
    			                   "/html/body/div[6]/div[3]/div/div/div/div[2]/ol/li[5]/div[2]/div/button" };
    	// Continue
    	for (int i = 0 ; i < 4; i++) {
    		
    		Thread.sleep(1000);
    		driver.findElement(By.xpath(continueButtonXpathsList[i])).click();
        	Thread.sleep(500);
    		
    	}
    	
    	Actions action = new Actions(driver);
    	WebElement totalLabel = totalLabel();
    	action.moveToElement(totalLabel).perform();
    	
    	placeOrderButton().click();
    	Thread.sleep(3000);
		
	}
	
	public WebElement getOrderMessage() {return orderMessage ();}

	public String getSuccessMessage() {return messageBar().getText();}
	
	public void selectCategory() {bookCategory().click();}
	
	public WebDriver getDriver() {return driver;}

}
