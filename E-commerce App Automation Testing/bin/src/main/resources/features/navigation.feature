#Author: shimaa.mohammed.badawy@gmail.com

@Regression
@NavigationTest
Feature: Navigating throughout the website

Scenario: Test search for a product
Given User is on homepage
When User search for a product
Then User get similar products as result 

Scenario: Test change currency to Euro
Given User is on homepage 
When User change currency to Euro
Then All prices are shown in Euro
  
Scenario: Test change currency to USD
Given User is on homepage
When User change currency to USD
Then All prices are shown in USD  

Scenario: Test select category
Given User is on homepage
When User select a category or a subcategory
Then Category page is shown 

Scenario: Test filter by color
Given User is on homepage
And User go to shoes subcategory
When User filter by a color
Then Products with same color appears 

Scenario: Test select a tag
Given User is on homepage
And User select a category or a subcategory
When User select a tag
Then Products with same tag appears 



     