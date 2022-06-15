package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.PageFactory;

import udacity.project2.globalVariables;

public class HomePage {
	
	WebDriver driver ;
	String selectedCategory;
	
	public HomePage(WebDriver driver, boolean navigateToHome) throws InterruptedException {
		
		this.driver = driver;
		driver.navigate().to("https://demo.nopcommerce.com");
		Thread.sleep(2000);
		
		PageFactory.initElements(this.driver, this);
		
	}
	
	public HomePage(WebDriver driver) throws InterruptedException {
		
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		
	}
	
	private WebElement searchBar() {return driver.findElement(By.xpath("//*[@id=\"small-searchterms\"]"));}
	private WebElement searchButton () { return driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[2]/form/button"));}
	private WebElement productImage() {return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[1]/div/div[1]/a/img"));}
	private WebElement currencyMenu () { return driver.findElement(By.xpath("//*[@id=\"customerCurrency\"]"));}
	private WebElement priceText() { return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div/div[4]/div[2]/div[1]/div/div[2]/div[3]/div[1]/span"));}
	private WebElement category(String categoryXpath) {return driver.findElement(By.xpath(categoryXpath));}
	private WebElement selectedCat(String xpath) {return driver.findElement(By.xpath(xpath+"/a"));}
	private WebElement subCategory(String subcategoryXpath) { return driver.findElement(By.xpath(subcategoryXpath));}
    private WebElement categoryHeader() {return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[1]/h1"));}
	private void setSelectedCategory(String cat) {this.selectedCategory = cat;}
	private WebElement filterResult(String xpath) {return driver.findElement(By.xpath(xpath));}
	private WebElement flterResultProductImage() {return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div/div/div[1]/a/img"));}
    private WebElement bookTag() {return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div[3]/div[2]/div[1]/ul/li[3]"));}
    private WebElement tagHeader() throws InterruptedException { Thread.sleep(2000); return driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div/div[1]/h1"));}
    
    private WebElement currencyItem (char currency) { 
		String baseXpath = "/html/body/div[6]/div[1]/div[1]/div[1]/div/select/option[";
		if ( currency == 'E') {baseXpath = baseXpath + "2]";}
		else baseXpath = baseXpath + "1]";
		
		return driver.findElement(By.xpath(baseXpath));
				
	}
	
	public void doSearch() throws InterruptedException {
		
		searchBar().sendKeys("apple");

		searchButton().click();
    	Thread.sleep(globalVariables.sleepTime);
		
	}
	
	public WebElement searchResult() { return productImage(); }
	
	public void changeCurrencyTo(char currency) throws InterruptedException {
				
		currencyMenu().click();
		currencyItem(currency).click();
    	Thread.sleep(globalVariables.sleepTime);
		    	
	}
	
	public String getCurrentCurrency() { 
		
		String price = priceText().getText().replace(",", "");
		price = price.substring(0,price.indexOf("."));
		return price;
		
	}
	
	public String getSelectedCategory() {return this.selectedCategory;}
	
	public void selectCategory() throws InterruptedException {

		Integer categoryNumber = globalVariables.randomObj.nextInt(7)+1;
		String categoryXpath = "/html/body/div[6]/div[2]/ul[1]/li[" + categoryNumber.toString() + "]";
		String subcategoryXpath = categoryXpath + "/ul/li[1]";
		
    	// Go to the selected category's location
    	Actions action = new Actions(driver);
    	WebElement category = category(categoryXpath);
    	action.moveToElement(category).perform();
  
    	Thread.sleep(globalVariables.sleepTime);    	
    	
    	// If there is a sub-category, click on it, else click on the category it self
    	try {
    		WebElement subCategory = subCategory(subcategoryXpath);
    		setSelectedCategory(selectedCat(subcategoryXpath).getText());
    		subCategory.click();
    	} catch (Exception e) {
    		setSelectedCategory(selectedCat(categoryXpath).getText());
    		category.click();
    	}

    	Thread.sleep(2000);
		
	}
	
	public String getCategoryHeader() { return categoryHeader().getText();}
	
	public void goToShoesCategory() {
		
		WebElement appealCategory = category("/html/body/div[6]/div[2]/ul[1]/li[3]");
    	Actions action = new Actions(driver);
    	action.moveToElement(appealCategory).perform();
    	
    	// Shoes sub menu
    	subCategory("/html/body/div[6]/div[2]/ul[1]/li[3]/ul/li[1]").click();
		
	}
	
	public void filterByRed() { filterResult("//*[@id=\"attribute-option-15\"]").click();}
	
	public WebElement getProductImage() { return flterResultProductImage(); }
	
	public void selectBookTag () throws InterruptedException { Thread.sleep(2000) ;bookTag().click();}
	
	public String getTagHeader() throws InterruptedException {return tagHeader().getText();}
	
	public void exitDrive() { driver.quit(); }


}
