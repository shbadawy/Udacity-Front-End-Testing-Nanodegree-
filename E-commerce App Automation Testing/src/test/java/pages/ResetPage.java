package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import udacity.project2.globalVariables;

public class ResetPage {
	
	WebDriver driver;
	
	public ResetPage(WebDriver driver) throws InterruptedException {
		
		globalVariables.setSystemProberty();
		this.driver = driver;
		
		this.driver.navigate().to("https://demo.nopcommerce.com/passwordrecovery");
		Thread.sleep(2000);
		
		PageFactory.initElements(this.driver, this);
		
	}
	
	private WebElement emailTextBox () {return driver.findElement(By.xpath("//*[@id=\"Email\"]"));}
	private WebElement recoverButton () {return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/form/div[2]/button"));}

	public void recoverEmail () throws InterruptedException {
		
		emailTextBox().sendKeys(globalVariables.email);
		recoverButton().click();
		Thread.sleep(globalVariables.sleepTime);
		
	}
	
	public String getConfirmationMessage () {return driver.findElement(By.xpath("/html/body/div[5]/div/p")).getText();}
	
	public WebDriver getDriver() {return driver;}
	
}
