package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

WebDriver driver ;
	
	private WebElement messageBar() {return driver.findElement(By.xpath("/html/body/div[5]/div/p"));}
	private WebElement addToWishlistButton() {return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div[2]/div[3]/div[2]/button[3]"));}
	private WebElement addToCompareListButton() {return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div[2]/div[3]/div[2]/button[2]"));}
	private WebElement bookCategory() {return driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[5]"));}
	

	public ProductPage (WebDriver driver) throws InterruptedException {
		
		this.driver = driver;
		this.driver.navigate().to("https://demo.nopcommerce.com");
		Thread.sleep(2000);
		PageFactory.initElements(this.driver, this);
		
	}
	
	public void addToWishlist () throws InterruptedException {
		
		addToWishlistButton().click();
		Thread.sleep(1000);
		
	}
	
	public void addToComparList () throws InterruptedException {
		
		addToCompareListButton().click();
		Thread.sleep(1000);
		
	}
	
	public String getSuccessMessage() {return messageBar().getText();}
	
	public void selectCategory() {bookCategory().click();}
	
	
}
