Feature: Functional Automation Testing on Puma Website

Description: Cucumber Test for Fab India Onsite Shopping Store
  @SmokeTest
  Scenario: Login & Logout feature of Fab India Onsite Shopping Store

    Given user launches the URL
    When user sign in to the application
    And user clicks on Account Info
    And user signs out of the application

  @SmokeTest1
  Scenario Outline: Login & Logout feature of Fab India Onsite Shopping Store

    Given user launches the URL
    When User Sign into the application with <Email> and <Password>
    And user clicks on Account Info
    And user signs out of the application
    Examples:
      | Email                   | Password     |
      | rajchozhan024@gmail.com | Chola@761645 |


  @SmokeTest1
  Scenario Outline: Verify UI Elements of Hompage in Fab India Onsite Shopping Store

    Given user launches the URL
    When User Sign into the application with <Email> and <Password>
    Then User verify the UI Elements in Homepage
    And user clicks on Account Info
    And user signs out of the application
    Examples:
      | Email                   | Password     |
      | rajchozhan024@gmail.com | Chola@761645 |