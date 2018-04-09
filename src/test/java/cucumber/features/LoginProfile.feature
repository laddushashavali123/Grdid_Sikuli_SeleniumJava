@LoginProfile
Feature: Login Profile
  As an employee of the company
  I want to login my employee profile using my credentials
  In order to collaborate with my colleagues

  Background: User navigates to Company home page
    Given I am on the Login page on URL "http://executeautomation.com/demosite/Login.html"
    Then I should see "Login" message

  Scenario: Successful login
    When I fill in "UserName" with "Test"
    And I fill in "Password" with "123"
    And I click on the "Login" button
    Then I should see "User Form" message

