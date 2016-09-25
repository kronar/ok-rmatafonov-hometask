Feature: Actions with Private Data
  As a user I should be able to change my Private Data
  I should not be able to input wrong data
  I should be able to cancel my changes with an appropriate button or by just closing an edit form

  Background:
    Given I am a user with name "parrot.kesha"

  Scenario: Change users private data
    When I navigate to the site and log in
    And I click the link Change Settings at the Home Page
    And I click the link Change in the section Private Data at the Settings Page
    And I set a random Name of 5 symbols in the form Change Private Data at the Settings Page
    And I set a random Surname of 7 symbols in the form Change Private Data at the Settings Page
    And I set the next Day, Month and Year for the Birth Date in the form Change Private Data at the Settings Page
    And I change the Gender in the form Change Private Data at the Settings Page
    And I input a letter into the City of Residence and select some value in the form Change Private Data at the Settings Page
    And I input a letter into the Hometown and select some value in the form Change Private Data at the Settings Page
    And I click button Save in the form Change Private Data at the Settings Page
    And I click button Close in the notification Private Data Changed at the Settings Page
    Then I should see the section Private Data formatted by the current language template with the changed data at the Settings Page
    When I navigate to the Home Page
    Then I should see the changed name truncated to 16 symbols with the changed surname truncated to 24 symbols at the Home Page
    And I should see the changed age with the City of Residence at the Home Page
    And I should see the changed Hometown at the Home Page

  Scenario: Incorrect input - long name and surname
    When I navigate to the site and log in
    And I click the link Change Settings at the Home Page
    And I click the link Change in the section Private Data at the Settings Page
    And I set a random Name of 17 symbols in the form Change Private Data at the Settings Page
    And I set a random Surname of 25 symbols in the form Change Private Data at the Settings Page
    And I store the current data from the fields in the form Change Private Data at the Settings Page
    And I input the stored City of Residence and select it with storing the long name in the form Change Private Data at the Settings Page
    And I input the stored Hometown and select it with storing the long name in the form Change Private Data at the Settings Page
    And I click button Save in the form Change Private Data at the Settings Page
    And I click button Close in the notification Private Data Changed at the Settings Page
    Then I should see the section Private Data formatted by the current language template with the stored data and changed name and surname at the Settings Page
    When I navigate to the Home Page
    Then I should see the changed name truncated to 16 symbols with the changed surname truncated to 24 symbols at the Home Page
    And I should see the stored age with the City of Residence at the Home Page
    And I should see the stored Hometown at the Home Page

  Scenario: Incorrect input - nonexistent location
    When I navigate to the site and log in
    And I click the link Change Settings at the Home Page
    And I click the link Change in the section Private Data at the Settings Page
    And I input "foobarfoobar" into City of Residence in the form Change Private Data at the Settings Page
    And I input "barfoobarfoo" into Hometown in the form Change Private Data at the Settings Page
    And I click button Save in the form Change Private Data at the Settings Page
    Then I should see the error message under the City of Residence in the form Change Private Data at the Settings Page:
    """
    Пожалуйста, выберите место проживания из списка
    """
    And I should see the error message under the Hometown in the form Change Private Data at the Settings Page:
    """
    Пожалуйста, выберите место проживания из списка
    """

  Scenario: Cancel changes with button Cancel
    When I navigate to the site and log in
    And I click the link Change Settings at the Home Page
    And I click the link Change in the section Private Data at the Settings Page
    And I store the current data from the fields in the form Change Private Data at the Settings Page
    And I input the stored City of Residence and select it with storing the long name in the form Change Private Data at the Settings Page
    And I input the stored Hometown and select it with storing the long name in the form Change Private Data at the Settings Page
    And I set a random Name of 5 symbols in the form Change Private Data at the Settings Page
    And I set a random Surname of 7 symbols in the form Change Private Data at the Settings Page
    And I set the next Day, Month and Year for the Birth Date in the form Change Private Data at the Settings Page
    And I change the Gender in the form Change Private Data at the Settings Page
    And I input a letter into the City of Residence and select some value in the form Change Private Data at the Settings Page
    And I input a letter into the Hometown and select some value in the form Change Private Data at the Settings Page
    And I click button Cancel in the form Change Private Data at the Settings Page
    Then I should see the section Private Data formatted by the current language template with the stored data at the Settings Page
    When I navigate to the Home Page
    Then I should see the stored name truncated to 16 symbols with the stored surname truncated to 24 symbols at the Home Page
    And I should see the stored age with the City of Residence at the Home Page
    And I should see the stored Hometown at the Home Page