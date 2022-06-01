package stepDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import udacity.project2.globalVariables;

public class DriverOps {

		
	public void exitDriver( WebDriver driver) {driver.quit();}
		
	public WebDriver startDriver () {
		
		globalVariables.setSystemProberty();
		WebDriver driver = new FirefoxDriver(); 
		return driver;
		
	}
	
}
