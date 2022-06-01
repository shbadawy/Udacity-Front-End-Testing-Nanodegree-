package stepDefinition;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.RegisterPage;
import pages.ResetPage;

public class ResetPasswordStepDefinition {
	
	RegisterPage register;
	ResetPage reset;
	WebDriver driver;
	DriverOps driverOps;
	
	@Given("User is already registered")
	public void user_already_registered() throws InterruptedException {
		
		register = new RegisterPage(driver);
		register.addUserInformation();
		
	}
	
	@And("User go to reset password page")
	public void user_on_reset_page() throws InterruptedException {
		
		reset = new ResetPage(driver);
		
	}
	
	@When("Enter email and click reset button")
	public void enter_email_and_click_reset() throws InterruptedException {
		
		reset.recoverEmail();
		
	}
	
	@Then("A reset email is sent to the user email")
	public void send_reset_email() {assertEquals("Email with instructions has been sent to you.", reset.getConfirmationMessage());}

	 @Before("@ResetPasswordTest")
	  public void startDrive() {driverOps = new DriverOps(); this.driver = driverOps.startDriver();}
	  
	  @After("@ResetPasswordTest")
	  public void exitDriver() { driverOps.exitDriver(driver);}
	  
}
