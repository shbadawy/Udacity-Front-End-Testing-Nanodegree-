package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BannerPage {
	
	WebDriver driver ;
	
	private WebElement get_banner_item(Integer i) {

		String xpath = "/html/body/div[6]/div[3]/div/div/div/div/div[1]/div[2]/a[" + Integer.toString(i) + "]";
		return driver.findElement(By.xpath(xpath));
		
	}
	
	private WebElement get_banner() {return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div/div[1]/div[1]/a[1]")); }
	
	public BannerPage (WebDriver driver) throws InterruptedException {
		
		this.driver = driver;
		this.driver.navigate().to("https://demo.nopcommerce.com");
		Thread.sleep(2000);
		PageFactory.initElements(this.driver, this);
		
	}
	
	public void click_item(Integer i) {get_banner_item(i).click();get_banner().click();}
	
	public String get_url() {return driver.getCurrentUrl();}
	

}
