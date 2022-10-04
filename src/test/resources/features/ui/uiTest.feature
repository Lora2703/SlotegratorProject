Feature: As admin user I can log in the app and sort the players table

  Background: Login with admin credentials
    When User enters the login in Login input field on Login Page
    And User enters the password in Password input field on Login Page
    And User clicks on the Sign in button on Login Page
    Then Casino Dashboard Page in opened

  Scenario: Open and sort players table
    When User clicks on the Users button on Dashboard Sidebar
    And User clicks on the Players button on Dashboard Sidebar
    Then User see the Players table on Player management Page
    And User enters name in the Player name search field in the header of the table on Player management page
    Then Players table is sorted by the names