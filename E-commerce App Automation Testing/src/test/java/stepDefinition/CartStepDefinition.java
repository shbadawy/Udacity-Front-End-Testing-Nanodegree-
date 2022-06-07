package stepDefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
//import pages.HomePage;
import pages.RegisterPage;

public class CartStepDefinition {

	RegisterPage register;
	WebDriver driver = Hooks.driver; 
	CartPage cart;
	 
	
	@Given("User register successfully")
	public void user_registered_successfully() throws InterruptedException {
		
		register = new RegisterPage(driver);
		register.addUserInformation();
		cart = new CartPage(driver);
		
	}
	
	@Given("User select a category")
	public void select_category() throws InterruptedException {
		
		driver = Hooks.driver;
		cart = new CartPage(driver,true);
		cart.selectCategory();
		
	}
	
	@And("User selected a category")
	public void select_category_() throws InterruptedException {cart.selectCategory();}
	
	
	  @When ("User adds a product to the cart")
	  public void user_add_to_cart () throws InterruptedException { cart.addToCart(); }
	  
	  @Then ("Item is added to cart")
	  public void  item_is_added_to_cart(){ assertEquals("The product has been added to your shopping cart", cart.getSuccessMessage()); }
	  
	  
	  
	  @And ("Place an order")
	  public void place_an_order() throws InterruptedException{cart.createOrder();}
	  
	  
	  @Then ("The order is placed successfully")
	  public void order_succeeded(){assertNotEquals(null, cart.getOrderMessage());}
		  
	
	  
}
