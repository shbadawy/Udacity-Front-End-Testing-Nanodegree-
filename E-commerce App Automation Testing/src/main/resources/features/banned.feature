#Author: shimaa.mohammed.badawy@gmail.com

@Regression
@bannerTest
Feature: Test different cart operation

Scenario: First banner test
  Given User is at home page 
  When User clicks first banner
  Then User go to first product page
  
Scenario: Second banner test
  Given User is at home page 
  When User clicks second banner
  Then User go to second product page