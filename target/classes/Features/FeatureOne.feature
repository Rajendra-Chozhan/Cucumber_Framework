Feature: Functional Automation Testing on Fab India Website

  Description: Cucumber Test for Fab India Onsite Shopping Store

  @SmokeTest @TestNG
  Scenario: Login & Logout feature

    Given user launches the URL
    When user logs in as "default"
    And user clicks on Account Info
    And user signs out of the application

  @SmokeTest
  Scenario Outline: Login & Logout with different users

    Given user launches the URL
    When user logs in as "<userType>"
    And user clicks on Account Info
    And user signs out of the application

    Examples:
      | userType |
      | default  |

  @SmokeTest
  Scenario Outline: Verify UI Elements in Homepage

    Given user launches the URL
    When user logs in as "<userType>"
    Then User verify the UI Elements in Homepage
    And user clicks on Account Info
    And user signs out of the application

    Examples:
      | userType |
      | default  |