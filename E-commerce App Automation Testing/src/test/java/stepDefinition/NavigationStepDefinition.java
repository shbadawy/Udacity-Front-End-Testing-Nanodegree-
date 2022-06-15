package stepDefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import java.lang.Math;

public class NavigationStepDefinition {
	
	HomePage home;
	WebDriver driver = Hooks.driver;
	double dollarEuroRatio = 1.162790697674419;
	double oldPrice;

	@Given ("User is on homepage")
	public void user_is_on_homepage() throws InterruptedException {home = new HomePage(driver, true);}
	
	@And ("currency is Euro")
	public void changeToEuro() throws InterruptedException {home.changeCurrencyTo('E');}
	
	@When ("User search for a product")
	public void user_search_for_product() throws InterruptedException {home.doSearch();}
	
	@Then ("User get similar products as result")
	public void userget_similar_products_result() {assertNotEquals(null, home.searchResult());}
	
	@When ("User change currency to Euro")
	public void user_change_currency_to_Euro() throws InterruptedException {
		
		oldPrice = Double.parseDouble(home.getCurrentCurrency().substring(1));
		home.changeCurrencyTo('E');

	}
	
	@Then ("All prices are shown in Euro")
	public void prices_in_euro() {
		
		String newPrice = home.getCurrentCurrency();
		assertTrue(newPrice.contains("€"));
		assertEquals((int) Math.ceil(oldPrice/dollarEuroRatio) , Integer.parseInt(newPrice.substring(1)));
		
	}
	
	@When ("User change currency to USD")
	public void user_change_to_usd() throws InterruptedException { 
		
		oldPrice = Double.parseDouble(home.getCurrentCurrency().substring(1));
		home.changeCurrencyTo('D');
		
	}
	
	@Then ("All prices are shown in USD")  
	public void prices_in_usd() { 
		
		String newPrice = home.getCurrentCurrency();
		assertTrue(newPrice.contains("$"));
		assertEquals((int) Math.floor(oldPrice*dollarEuroRatio) , Integer.parseInt(newPrice.substring(1)));
		
	}
		
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
	  
}
