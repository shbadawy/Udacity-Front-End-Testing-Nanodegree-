package stepDefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class NavigationStepDefinition {
	
	HomePage home;
	WebDriver driver = Hooks.driver;;

	@Given ("User is on homepage")
	public void user_is_on_homepage() throws InterruptedException {home = new HomePage(driver, true);}
	
	@When ("User search for a product")
	public void user_search_for_product() throws InterruptedException {home.doSearch();}
	
	@Then ("User get similar products as result")
	public void userget_similar_products_result() {assertNotEquals(null, home.searchResult());}
	
	@When ("User change currency to Euro")
	public void user_change_currency_to_Euro() throws InterruptedException {home.changeCurrencyTo('E');}
	
	@Then ("All prices are shown in Euro")
	public void prices_in_euro() {assertTrue(home.getCurrentCurrency().contains("€"));}
	
	@When ("User change currency to USD")
	public void user_change_to_usd() throws InterruptedException { home.changeCurrencyTo('D');}
	
	@Then ("All prices are shown in USD")  
	public void prices_in_usd() { assertTrue(home.getCurrentCurrency().contains("$"));}
		
	@When ("User select a category or a subcategory")
	public void user_select_category() throws InterruptedException { home.selectCategory(); }
	
	@Then ("Category page is shown")
	public void show_category_page() {
		
		String selectedCategory = home.getSelectedCategory();
		String headerTitle = home.getCategoryHeader();
		
		assertEquals(selectedCategory, headerTitle);
		
	}
	
	@And ("User go to shoes subcategory")
	public void go_to_shoes_category() { home.goToShoesCategory();}
	
	@When ("User filter by a color")
	public void filter_by_color() {home.filterByRed();}
	
	@Then ("Products with same color appears") 
	public void show_product_with_selected_color() {assertNotEquals(null, home.getProductImage());}
	
	@When ("User select a tag")
	public void user_select_tag() throws InterruptedException {home.selectBookTag();}
	
	@Then ("Products with same tag appears") 
	public void show_product_with_selected_tag() throws InterruptedException {assertEquals(home.getTagHeader(), "Products tagged with 'book'");}
	
//	@Before("@NavigationTest")
//	  public void startDrive() {driverOps = new DriverOps(); this.driver = driverOps.startDriver();}
//	  
//	  @After("@NavigationTest")
//	  public void exitDriver() { driverOps.exitDriver(driver);}
	  
}
