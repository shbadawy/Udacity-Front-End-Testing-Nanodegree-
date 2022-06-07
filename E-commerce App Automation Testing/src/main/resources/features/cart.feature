#Author: shimaa.mohammed.badawy@gmail.com

@Regression
@CartTest
Feature: Test different cart operation

Scenario: Add to cart
  Given User select a category 
  When User adds a product to the cart
  Then Item is added to cart
  
Scenario: Place an order
  Given User register successfully 
  And User selected a category
  When User adds a product to the cart
  And Place an order
  Then The order is placed successfully
