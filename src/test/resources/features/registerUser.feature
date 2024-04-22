Feature: Supporter account registration form

  Scenario Outline: Register a user
    Given I have opened the site in "<browser>"
    And I have entered a date of birth
    And I have entered first name "Sam"
    And I <nameStatus> entered last name "Smith"
    And I have entered a unique email address
    And I have entered the email in the confirmation field
    And I have entered a valid password
    And I entered the password <passStatus> in the confirmation field
    And I <tCStatus> selected agree to terms and conditions
    And I have selected to confirm age limit
    And I have agreed to the code of conduct
    When I click the register user button
    Then I see the <status> message

    Examples:
      | browser | nameStatus | passStatus  | tCStatus | status            |
      | chrome  | have       | correctly   | have     | confirmation      |
      | chrome  | have not   | correctly   | have     | missing last name |
      | safari  | have       | incorrectly | have     | wrong password    |
      | firefox | have       | correctly   | have not | terms not checked |


