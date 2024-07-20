Feature: API Automation
  As a user of the OpenCart website
  I want to be able to log in with my account

    Scenario: Create user
    Given : payload and bearer token
    When : send the post request
    Then : Validate the response code

