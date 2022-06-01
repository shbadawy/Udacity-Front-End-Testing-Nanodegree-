#Author: shimaa.mohammed.badawy@gmail.com

@Regression
@ResetPasswordTest
Feature: Test reset password for registered user

Scenario: Title of your scenario
  Given User is already registered
  And User go to reset password page
  When Enter email and click reset button
  Then A reset email is sent to the user email
