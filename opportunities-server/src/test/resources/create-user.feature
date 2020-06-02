Feature: Create User

  Scenario: WITH ALL REQUIRED FIELDS IS SUCCESSFUL

    Given user with the following attributes
      |name    | email                |
      |Rachel  | rachel.green@fs.com  |
      |Rachel1 | rachel1.green@fs.com |
      |Rachel2 | rachel2.green@fs.com |
      |Rachel3 | rachel3.green@fs.com |
      |Rachel4 | rachel4.green@fs.com |
      |Rachel5 | rachel5.green@fs.com |

    When user saves 'WITH ALL REQUIRED FIELDS'
    Then the save 'IS SUCCESSFUL'