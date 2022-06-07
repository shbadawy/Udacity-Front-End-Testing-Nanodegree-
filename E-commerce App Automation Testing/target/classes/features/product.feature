#Author: shimaa.mohammed.badawy@gmail.com

@Regression
@ProductTest
Feature: Test different product operation
  
Scenario: Add to wishlist
  Given User select category
  When User adds a product to the wishlist
  Then Item is added to wishlist
   
Scenario: Add to campare list
  Given User select category
  When User adds a product to the compare list
  Then Item is added to compare list
  