#Author: shimaa.mohammed.badawy@gmail.com

@Regression
@followTest
Feature: Test different following
  
Scenario: follow on youtube
Given User on home page
When User click on youtube icon
Then User is taken to youtube page

Scenario: follow on rss
Given User on home page
When User click on rss icon
Then User is taken to rss page

Scenario: follow on facebook
Given User on home page
When User click on facebook icon
Then User is taken to facebook page
  
Scenario: follow on twitter
Given User on home page
When User click on twitter icon
Then User is taken to twitter page