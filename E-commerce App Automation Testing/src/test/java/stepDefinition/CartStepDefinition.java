package stepDefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
//import pages.HomePage;
import pages.RegisterPage;

public class CartStepDefinition {

	RegisterPage register;
	WebDriver driver;
	CartPage cart;
	
	@Given("User register successfully")
	public void user_registered_successfully() throws InterruptedException {
		
		register = new RegisterPage();
		register.addUserInformation();
		driver = register.getDriver();
		cart = new CartPage(driver);
		
	}
	
	@Given("User select a category")
	public void select_category() throws InterruptedException {
		
		cart = new CartPage();
		cart.selectCategory();
		
	}
	
	@And("User selected a category")
	public void select_category_() throws InterruptedException {cart.selectCategory();}
	
	
	  @When ("User adds a product to the cart")
	  public void user_add_to_cart () throws InterruptedException { cart.addToCart(); }
	  
	  @Then ("Item is added to cart")
	  public void  item_is_added_to_cart(){ assertEquals("The product has been added to your shopping cart", cart.getSuccessMessage()); }
	  
	  
	  @When ("User adds a product to the wishlist")
	  public void  user_add_to_wishlist () throws InterruptedException{cart.addToWishlist();}
	  
	  @Then ("Item is added to wishlist")
	  public void  item_is_added_to_wishlist(){assertEquals("The product has been added to your wishlist", cart.getSuccessMessage());}
	  
	  
	  @When ("User adds a product to the compare list")
	  public void  user_add_to_comparelist() throws InterruptedException{ cart.addToComparList();}
	  	  
	  @Then ("Item is added to compare list")
	  public void  item_is_added_to_comparelist(){ assertEquals("The product has been added to your product comparison", cart.getSuccessMessage());}
	  
	  @And ("Place an order")
	  public void place_an_order() throws InterruptedException{cart.createOrder();}
	  
	  
	  @Then ("The order is placed successfully")
	  public void order_succeeded(){assertNotEquals(null, cart.getOrderMessage());}
	  
	  @After()
	  public void exitDriver() { try {cart.getDriver().quit();} catch(Exception e) {}}
		  
	
	  
}
