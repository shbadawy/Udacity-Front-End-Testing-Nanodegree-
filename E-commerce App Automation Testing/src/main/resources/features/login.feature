#Author: shimaa.mohammed.badawy@gmail.com

@Regression
Feature: Test user login

Scenario: Test user successful login
Given User registered successfully
And User logout
And User on the login page
When Enter username and password and click the login button
Then User login successfully
