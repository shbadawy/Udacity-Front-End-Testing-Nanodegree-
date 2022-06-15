package stepDefinition;


import static org.junit.Assert.assertEquals;
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
  public void click_facebook () throws InterruptedException  {follow.facebook_click();}
  
  @When ("User click on twitter icon")
  public void click_twitter () throws InterruptedException  {follow.twitter_click(); }
  
  @When ("User click on youtube icon")
  public void click_youtube () throws InterruptedException  {follow.youtube_click(); }

  @When ("User click on rss icon")
  public void click_rss () throws InterruptedException  {follow.rss_click(); }
  
  
  @Then ("User is taken to facebook page")
  public void go_to_facebook(){ assertTrue(follow.getNewTabURL(true).equals("https://www.facebook.com/nopCommerce")); }
  
  @Then ("User is taken to twitter page")
  public void go_to_twitter(){ assertTrue(follow.getNewTabURL(true).equals("https://twitter.com/nopCommerce")); }
  
  @Then ("User is taken to youtube page")
  public void go_to_youtube(){ assertEquals("https://www.youtube.com/user/nopCommerce", follow.getNewTabURL(true)); }
  
  @Then ("User is taken to rss page")
  public void go_to_rss(){ assertEquals("https://demo.nopcommerce.com/", follow.getNewTabURL(false)); }
	  
	 
}
