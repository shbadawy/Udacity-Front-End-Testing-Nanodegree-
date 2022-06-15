package stepDefinition;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BannerPage;

public class BannerStepDefinition {
	
	WebDriver driver = Hooks.driver;
	BannerPage banner;
	
	 @Given ("User is at home page")
	 public void go_to_home_page() throws InterruptedException {banner = new BannerPage(driver);}
	 
	 @When("User clicks first banner")
	 public void click_first_banner() {banner.click_item(1);}
	  
	 @Then("User go to first product page")
	 public void go_to_first_product() {assertEquals("https://demo.nopcommerce.com/iphone", banner.get_url());}
	 
	 @When ("User clicks second banner")
	 public void click_second_banner() {banner.click_item(2);}
	 
	 @Then("User go to second product page")
	 public void go_to_second_product() {assertEquals("https://demo.nopcommerce.com/nokia-lumia-1020", banner.get_url());}

}
