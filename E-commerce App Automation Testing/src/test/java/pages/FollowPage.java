package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class FollowPage {
	
	WebDriver driver;
	
	public FollowPage (WebDriver driver) throws InterruptedException {
		
		this.driver = driver;
		driver.navigate().to("https://demo.nopcommerce.com");
		Thread.sleep(2000);
		
		PageFactory.initElements(this.driver, this);
		
	}
	
	public void facebook_click() throws InterruptedException {
		
		driver.findElement(By.xpath("/html/body/div[6]/div[4]/div[1]/div[4]/div[1]/ul/li[1]/a")).click();
		Thread.sleep(3000);
		
	}
	
	public void twitter_click() throws InterruptedException {
		
		driver.findElement(By.xpath("/html/body/div[6]/div[4]/div[1]/div[4]/div[1]/ul/li[2]/a")).click();
		Thread.sleep(3000);
		
	}
	
	public void youtube_click() throws InterruptedException {
		
		driver.findElement(By.xpath("/html/body/div[6]/div[4]/div[1]/div[4]/div[1]/ul/li[4]/a")).click();
		Thread.sleep(3000);
		
	}
	
	public void rss_click() throws InterruptedException {
		
		driver.findElement(By.xpath("/html/body/div[6]/div[4]/div[1]/div[4]/div[1]/ul/li[3]/a")).click();
		Thread.sleep(3000);
		
	}
	
	
	public String getNewTabURL(boolean openNewTab) {
		
		String url ;
		if (openNewTab) {
			
			List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
			//switch to new tab
			driver.switchTo().window(browserTabs.get(1));
			url = driver.getCurrentUrl();
			driver.close();
			driver.switchTo().window(browserTabs.get(0));
			
		}else {url = driver.getCurrentUrl();}
		
		return url;
		
	}
	

}
