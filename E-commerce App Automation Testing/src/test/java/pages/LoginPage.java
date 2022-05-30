package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import udacity.project2.globalVariables;

public class LoginPage {

	WebDriver driver ;
	
	public LoginPage (WebDriver driver) throws InterruptedException {
		
		globalVariables.setSystemProberty();
		this.driver = driver;
		
		this.driver.navigate().to("https://demo.nopcommerce.com/login"); 
		Thread.sleep(2000);
		
		PageFactory.initElements(this.driver, this);
		
	}
	
	private WebElement usernameTextbox() {return driver.findElement(By.xpath("//*[@id=\"Email\"]"));}
	
	private WebElement passwordTextBox() {return driver.findElement(By.xpath("//*[@id=\"Password\"]"));}
	
	private WebElement loginButton() { return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[2]/form/div[3]/button"));}
	
	private WebElement getLogoutButton() {return driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a"));}
	
	public String getLoginMessage () { return getLogoutButton().getText(); }
	
	public void login() {
		
		usernameTextbox().sendKeys(globalVariables.email);
		passwordTextBox().sendKeys(globalVariables.password);
		loginButton().click();
		
	}
	
	public void logout () {getLogoutButton().click();}
	
	public WebDriver getDriver() { return driver;}
}
