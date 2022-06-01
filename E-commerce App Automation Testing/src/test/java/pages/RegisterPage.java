package pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import udacity.project2.globalVariables;

public class RegisterPage {
	
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) throws InterruptedException {
		
		this.driver = driver;
		
		this.driver.navigate().to("https://demo.nopcommerce.com/register"); 
		Thread.sleep(2000);
		
		PageFactory.initElements(this.driver, this);
		
	}
	
	
	private WebElement femaleGender() {return driver.findElement(By.xpath("//*[@id=\"gender-female\"]"));}
	private WebElement firstNameTextBox() {return driver.findElement(By.xpath("//*[@id=\"FirstName\"]"));}
	private WebElement lastNameTextBox() {return driver.findElement(By.xpath("//*[@id=\"LastName\"]"));}
	private ArrayList<WebElement> dateList() {
		
		String baseXpath = "/html/body/div[6]/div[3]/div/div/div/div[2]/form/div[1]/div[2]/div[4]/div/select[";
		ArrayList<WebElement> result = new ArrayList<WebElement>();
		for (Integer i = 1 ; i< 4 ; i++) {
			
			result.add(driver.findElement(By.xpath(baseXpath+i.toString()+"]")));
			
		}
		return result;
		
	}
	
	private WebElement emailTextBox() {return driver.findElement(By.xpath("//*[@id=\"Email\"]"));}
	private WebElement passwordTextBox() { return driver.findElement(By.xpath("//*[@id=\"Password\"]"));}
	private WebElement passwordConfirmationTextBox() {return driver.findElement(By.xpath("//*[@id=\"ConfirmPassword\"]"));}
	private WebElement registerButton() {return driver.findElement(By.xpath("//*[@id=\"register-button\"]"));}
	
	public void addUserInformation() throws InterruptedException {
		
		globalVariables.setRandomID();
		Thread.sleep(2000);
		femaleGender().click();
    	Thread.sleep(globalVariables.sleepTime);
    	
    	firstNameTextBox().sendKeys("shimaa");
    	lastNameTextBox().sendKeys("badawy");
    	Thread.sleep(globalVariables.sleepTime);
    	
    	ArrayList<WebElement> dateList = dateList();
    	
    	dateList.get(0).sendKeys("2");
    	dateList.get(1).sendKeys("May");
    	dateList.get(2).sendKeys("1990");
    	Thread.sleep(globalVariables.sleepTime);
    	
    	emailTextBox().sendKeys(globalVariables.email);
    	passwordTextBox().sendKeys(globalVariables.password);
    	passwordConfirmationTextBox().sendKeys(globalVariables.password);
    	Thread.sleep(globalVariables.sleepTime);
    	
    	registerButton().click();
		
	}
	
	public String getRegisterationMessage() {
		
    	return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]")).getText();
		
	}
	
	private WebElement getLogoutButton() {return driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a"));}

	public void logout () {getLogoutButton().click();}
	
//	public WebDriver getDriver() { return driver;}
	
}
