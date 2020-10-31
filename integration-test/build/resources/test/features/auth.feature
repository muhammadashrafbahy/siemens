Feature: Auth feature

  Scenario: user authenticate with password success
    When user authenticate with password "test"
    Then the response status code should be 200
    And the json response has "token" object
    And the json response has "expiration" object

  Scenario: user authenticate with bad password
    When user authenticate with password "test1"
    Then the response status code should be 401
    And the json response has "apiError" object
