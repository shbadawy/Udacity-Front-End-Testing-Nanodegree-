#Author: shimaa.mohammed.badawy@gmail.com

@Regression
@RegisterTest
Feature: Test user registeration

Scenario: User registeration 
Given User is on the registeration page
When User enter registeration information and click register
Then The user registered succeessfully 
