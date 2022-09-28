Feature: Functional Automation Testing on Puma Website

Description: Cucumber Test for Puma Onsite Site
@SmokeTest
Scenario: Login & Logout feature of Puma 

Given user launches the URL
When user sign in to the application
And user clicks on Account Info
And user signs out of the application


@SmokeTest
Scenario Outline: Validation of Add to Cart Functionality in Puma website
Given user launches the URL
When User Sign into the application with <Email> and <Password>
And User Search for product
And User filters the product
And User Add item to Cart


Examples:
|Email                      |                    Password|
|rajchozhan024@gmail.com    |                Puma@761645 |