package stepDefinition;


import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FollowPage;

public class FollowStepDefinision {
	
	WebDriver driver = Hooks.driver; 
	FollowPage follow ;
	
	@Given("User on home page")
	public void go_to_home_page() throws InterruptedException {
		
		follow = new FollowPage(driver);
		
	}
	
	
	  @When ("User click on facebook icon")
	  public void click_facebook () throws InterruptedException  { follow.facebook_click(); }
	  
	  @When ("User click on twitter icon")
	  public void click_twitter () throws InterruptedException  {follow.twitter_click(); }
	  
	  
	  @Then ("User is taken to facebook page")
	  public void go_to_facebook(){ assertTrue(follow.getNewTabURL().contains("facebook")); }
	  
	  @Then ("User is taken to twitter page")
	  public void go_to_twitter(){ assertTrue(follow.getNewTabURL().contains("twitter")); }
	  
	  

}
