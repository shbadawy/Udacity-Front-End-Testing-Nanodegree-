#Author: shimaa.mohammed.badawy@gmail.com

@Regression
@BannerTest
Feature: Test different cart operation

Scenario: first banner test
  Given user on home page 
  When User click on first banner
  Then User go to product 1 page
  
Scenario: second banner test
  Given user on home page 
  When User click on second banner
  Then User go to product 2 page