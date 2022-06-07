package stepDefinition;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ProductPage;

public class ProductStepDefinition {
	
	WebDriver driver = Hooks.driver; 
	ProductPage product;
	
	@Given("User select category")
	public void select_category() throws InterruptedException {
		
		driver = Hooks.driver;
		product = new ProductPage(driver);
		product.selectCategory();
		
	}
	
	@When ("User adds a product to the wishlist")
	  public void  user_add_to_wishlist () throws InterruptedException{product.addToWishlist();}
	  
	  @Then ("Item is added to wishlist")
	  public void  item_is_added_to_wishlist(){assertEquals("The product has been added to your wishlist", product.getSuccessMessage());}
	  
	  
	  @When ("User adds a product to the compare list")
	  public void  user_add_to_comparelist() throws InterruptedException{ product.addToComparList();}
	  	  
	  @Then ("Item is added to compare list")
	  public void  item_is_added_to_comparelist(){ assertEquals("The product has been added to your product comparison", product.getSuccessMessage());}
	  

}
