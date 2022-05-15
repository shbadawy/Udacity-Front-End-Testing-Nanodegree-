package udacity.project2;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
	
	WebDriver driver = null;
	Random randomObj = new Random();
	Integer randID = randomObj.nextInt(10000000);
	String email = "shimaa.badawy" + randID.toString()+ "@test.com";
	String password = "123456789SB";
	int sleepTime = 100;
	
	@BeforeClass
	public void setupDrivers() throws InterruptedException {

		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/drivers/geckodriver");
		driver = new FirefoxDriver();
		driver.get("https://demo.nopcommerce.com/");
    	Thread.sleep(3000);
		
	}
	
	
    @Test
    public void registerTest() throws InterruptedException
    {
    	
    	driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[1]/a")).click();
    	
    	// Filling information
    	driver.findElement(By.xpath("//*[@id=\"gender-female\"]")).click();
    	Thread.sleep(sleepTime);
    	
    	driver.findElement(By.xpath("//*[@id=\"FirstName\"]")).sendKeys("shimaa");
    	driver.findElement(By.xpath("//*[@id=\"LastName\"]")).sendKeys("badawy");
    	Thread.sleep(sleepTime);
    	
    	driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/form/div[1]/div[2]/div[4]/div/select[1]")).sendKeys("2");
    	driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/form/div[1]/div[2]/div[4]/div/select[2]")).sendKeys("May");
    	driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/form/div[1]/div[2]/div[4]/div/select[3]")).sendKeys("1990");
    	Thread.sleep(sleepTime);
    	
    	driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(email);
    	driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(password);
    	driver.findElement(By.xpath("//*[@id=\"ConfirmPassword\"]")).sendKeys(password);
    	Thread.sleep(sleepTime);
    	
    	driver.findElement(By.xpath("//*[@id=\"register-button\"]")).click();
    	WebElement successText = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]"));
    	Thread.sleep(sleepTime);
    	
    	assertNotEquals(successText, null);
    	assertEquals(successText.getText(), "Your registration completed");
        
    }
    
    @Test(dependsOnMethods = "registerTest")
    public void passwordResetTest() throws InterruptedException {
    	
    	// Go to login page then to reset password page
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a")).click();
    	driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a")).click();
    	driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[2]/form/div[2]/div[3]/span/a")).click();
    	Thread.sleep(sleepTime);
    	
    	// Inserting email
    	driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(email);  	
    	driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/form/div[2]/button")).click();  	
    	Thread.sleep(sleepTime);
    	
    	WebElement successBar = driver.findElement(By.xpath("/html/body/div[5]/div/p"));
    	assertNotEquals(successBar, null);
    	assertEquals(successBar.getText(), "Email with instructions has been sent to you."); 

    }
    
    @Test(dependsOnMethods = "passwordResetTest")
    public void loginTest() throws InterruptedException {
    	driver.get("https://demo.nopcommerce.com");
    	Thread.sleep(3000);
    	
    	// Go to login page and filling information
    	driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a")).click();
    	Thread.sleep(sleepTime);
    	driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(email);
    	driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(password);
    	driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[2]/form/div[3]/button")).click();
    	Thread.sleep(sleepTime);
    	
    	// Check login status
    	WebElement logoutHomeButton = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a"));
    	assertNotEquals(logoutHomeButton, null);
    	assertEquals(logoutHomeButton.getText(), "Log out");

    }
    
    @Test(dependsOnMethods = "loginTest")
    public void searchTest() throws InterruptedException {
    	
    	Thread.sleep(sleepTime);
    	// Entering the search term
    	driver.findElement(By.xpath("//*[@id=\"small-searchterms\"]")).sendKeys("apple");
    	// Click search
    	driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[2]/form/button")).click();
    	Thread.sleep(sleepTime);
    	// Check result
    	WebElement productImage = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[1]/div/div[1]/a/img"));
    	assertNotEquals(productImage, null);
    	
    }
    
    @Test(dependsOnMethods = "searchTest")
    public void changeCurrencyTest() throws InterruptedException {
    	// Go to home page
    	driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a/img")).click();
    	
        // Change currency to Euro
    	driver.findElement(By.xpath("//*[@id=\"customerCurrency\"]")).click();
    	driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[1]/div/select/option[2]")).click();
    	Thread.sleep(sleepTime);
    	
        // Change currency to USD    	
    	WebElement priceText = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div/div[4]/div[2]/div[1]/div/div[2]/div[3]/div[1]/span"));
    	assertTrue(priceText.getText().contains("€"));
    	driver.findElement(By.xpath("//*[@id=\"customerCurrency\"]")).click();
    	driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[1]/div/select/option[1]")).click();
    	Thread.sleep(sleepTime);
    	
    	priceText = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div/div[4]/div[2]/div[1]/div/div[2]/div[3]/div[1]/span"));
    	assertTrue(priceText.getText().contains("$"));
    	
    }
    
    @Test(dependsOnMethods = "changeCurrencyTest")
    public void selectCategoryTest() throws InterruptedException {
    	Integer categoryNumber = randomObj.nextInt(7)+1;
    	String categoryXpath = "/html/body/div[6]/div[2]/ul[1]/li[" + categoryNumber.toString() + "]";
    	String subcategoryXpath = categoryXpath + "/ul/li[1]";

    	WebElement category = driver.findElement(By.xpath(categoryXpath));

    	// Go to the selected category's location
    	Actions action = new Actions(driver);
    	action.moveToElement(category).perform();
  
    	Thread.sleep(sleepTime);    	
    	
    	// If there is a sub-category, click on it, else click on the category it self
    	String selectedCategory = null;
    	try {
    		WebElement subCategory = driver.findElement(By.xpath(subcategoryXpath));
    		selectedCategory = driver.findElement(By.xpath(subcategoryXpath+"/a")).getText(); subCategory.click();
    	} catch (Exception e) {
    		selectedCategory = driver.findElement(By.xpath(categoryXpath+"/a")).getText();  category.click();
    	}

    	Thread.sleep(sleepTime);
    	// Check the header title of the selected category
    	String headerTitle = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[1]/h1")).getText();
    	assertEquals(selectedCategory, headerTitle);

    }

    @Test(dependsOnMethods = "selectCategoryTest")
    public void filterByColorTest() {
    	
    	WebElement appealCategory = driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[3]"));
    	Actions action = new Actions(driver);
    	action.moveToElement(appealCategory).perform();
    	
    	// Shoes sub menu
    	driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[3]/ul/li[1]")).click();
    	
    	// Red choice
    	driver.findElement(By.xpath("//*[@id=\"attribute-option-15\"]")).click();
    	
    	WebElement redShoesImage = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div/div/div[1]/a/img"));
    	assertNotEquals(redShoesImage, null);
    	
    }
    
    @Test(dependsOnMethods = "filterByColorTest")
    public void selectTagsTest() throws InterruptedException {
    	
    	Thread.sleep(3000);
    	
    	// Select Book tag
    	driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div[4]/div[2]/div[1]/ul/li[3]")).click();
    	
    	String headerText = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div/div[1]/h1")).getText();
    	assertEquals(headerText, "Products tagged with 'book'");
    	
    }
    
    @Test(dependsOnMethods = "selectTagsTest")
    public void addToCartTest() throws InterruptedException {
    	
    	// Add a product to cart
    	driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div[2]/div[3]/div[2]/button[1]")).click();
    	Thread.sleep(1000);
    	
    	// Check success message
    	String barText = driver.findElement(By.xpath("/html/body/div[5]/div/p")).getText();
    	assertEquals(barText, "The product has been added to your shopping cart");
    	
    }
    
    @Test(dependsOnMethods = "addToCartTest")
    public void addToWishlistTest() throws InterruptedException {
    	
    	// Add a product to wishlist
    	driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div[2]/div[3]/div[2]/button[3]")).click();
    	Thread.sleep(1000);
    	
    	// Check success message
    	String barText = driver.findElement(By.xpath("/html/body/div[5]/div/p")).getText();
    	assertEquals(barText, "The product has been added to your wishlist");
    	
    }
    
    @Test(dependsOnMethods = "addToWishlistTest")
    public void addToCompairListTest() throws InterruptedException {
    	
    	// Add a product to compair list
    	driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div[2]/div[3]/div[2]/button[2]")).click();
    	Thread.sleep(3000);
    	
    	// Check success message
    	String barText = driver.findElement(By.xpath("/html/body/div[5]/div/p")).getText();
    	assertEquals(barText, "The product has been added to your product comparison");
    	
    }
    
    @Test(dependsOnMethods = "addToCompairListTest")
    public void placeAnOrderTest() throws InterruptedException {
    	
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//*[@id=\"topcartlink\"]")).click();
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//*[@id=\"termsofservice\"]")).click();
    	driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
    	
    	// Set country, city and address
    	driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_CountryId\"]")).sendKeys("Egypt");
    	driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_City\"]")).sendKeys("Cairo");
    	driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_Address1\"]")).sendKeys("Tahanoub");
    	
    	// Set zip code and phone
    	driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_ZipPostalCode\"]")).sendKeys("80000");
    	driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_PhoneNumber\"]")).sendKeys("258280000");
    	
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
    	
    	driver.findElement(By.xpath( "/html/body/div[6]/div[3]/div/div/div/div[2]/ol/li[6]/div[2]/div[2]/button")).click();
    	Thread.sleep(3000);
    	WebElement orderMessage = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div/div[2]/div[2]/a"));
    	assertNotEquals(orderMessage, null);
    	
    }
    
    
    @AfterClass
    public void afterTests() {
    	
    	driver.quit();
    	
    }
}
