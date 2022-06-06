package stepDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	public static WebDriver driver;
	
	@Before
	public void startDriver() {
		
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/main/resources/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();	
	}
	
	@After
	public void closeDriver() {
		
		driver.quit();;		
	}

}
