Feature:
  This demo feature will login github

  Scenario: Login github with username and password
    Given I navigate to the login page
    And I enter  the username and the password
    And I click login button
    Then I should see userform page
