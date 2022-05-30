#Author: shimaa.mohammed.badawy@gmail.com

@Regression
Feature: Test different cart operation

Scenario: Add to cart
  Given User select a category 
  When User adds a product to the cart
  Then Item is added to cart
  
Scenario: Add to wishlist
  Given User select a category
  When User adds a product to the wishlist
  Then Item is added to wishlist
   
Scenario: Add to campare list
  Given User select a category
  When User adds a product to the compare list
  Then Item is added to compare list
  
Scenario: Place an order
  Given User register successfully 
  And User selected a category
  When User adds a product to the cart
  And Place an order
  Then The order is placed successfully
