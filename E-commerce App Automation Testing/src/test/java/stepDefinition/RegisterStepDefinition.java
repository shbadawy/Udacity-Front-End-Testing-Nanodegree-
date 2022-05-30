package stepDefinition;

import static org.junit.Assert.assertEquals;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import pages.RegisterPage;

public class RegisterStepDefinition {
	
	RegisterPage register;
	
	@Given("User is on the registeration page")
	public void user_go_to_registeration_page() throws InterruptedException {register = new RegisterPage();}
	
	@When("User enter registeration information and click register")
	public void user_enter_info_and_register() throws InterruptedException {register.addUserInformation();}
	
	 
	@Then("The user registered succeessfully")
	public void user_registered_successfuly() {assertEquals( "Your registration completed" , register.getRegisterationMessage());}
	
	@After 
	public void close_browser() {try {register.getDriver().quit();} catch(Exception e) {} }

}
