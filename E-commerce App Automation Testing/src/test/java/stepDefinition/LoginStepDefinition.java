package stepDefinition;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.RegisterPage;

public class LoginStepDefinition {

	RegisterPage register;
	LoginPage login;
	WebDriver driver;
	DriverOps driverOps;
	
	@Given("User registered successfully")
	public void user_registered_successfully() throws InterruptedException {
		
		register = new RegisterPage(driver);
		register.addUserInformation();
		
	}
	
	@And("User logout")
	public void user_logout() {
		
		register.logout();
		
	}
	
	@And("User on the login page")
	public void user_on_the_login_page() throws InterruptedException {
		
		login = new LoginPage(driver);
		
	}
	
	@When("Enter username and password and click the login button")
	public void enter_login_info_and_login() {
		
		login.login();
		
	}
	
	@Then("User login successfully")
	public void user_logged_in_successfully() {
		
		assertEquals("Log out", login.getLoginMessage());
		
	}
	
	 @Before("@LoginTest")
	  public void startDrive() {driverOps = new DriverOps(); this.driver = driverOps.startDriver();}
	  
	  @After("@LoginTest")
	  public void exitDriver() { driverOps.exitDriver(driver); };
	
	

}
