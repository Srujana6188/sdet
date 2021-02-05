@SuiteCRM

Feature: Testing the SuiteCRM application

Background: User opens the URL and logins
	Given User opens chrome Browser
	When Open the SuiteCRM page and login with credentials provided "admin" and "pa$$w0rd"

@TestCaseOne
Scenario: Counting Dashlets
Scenario Outline: Open the homepage and count the number of the dashlets on the page
    Given Count the number of Dashlets on the homepage
    When Print the number and title of each Dashlet into the console
    Then Close the browser

@TestCaseTwo
Scenario: Create leads using parameterization
Scenario Outline: Open the Leads page and add multiple lead accounts using values passed from Feature file
    Given Navigate to Sales -> Leads -> Create Lead
    When Fill in the necessary details to create lead accounts using the values passed from the Feature file as "Mr." and "SURESH1" and "BILLA1" and "suresh.uce574@gmail.com"
    Then Click Save to finish
    And Navigate to the View Leads page to see results as "MR." and "SURESH1" and "BILLA1"
    And Close the browser

@TestCaseThree
Scenario: Schedule a meeting and invite members
Scenario Outline: To schedule a meeting and include at least 3 invitees
    Given Navigate to Activities -> Meetings -> Schedule a Meeting
    And User first enters the"<FirstMember>"and add to meeting
    And User second enters the"<SecondMember>"and add to meeting
    And User third enters the"<ThirdMember>"and add to meeting
    And Click On Save
    And Navigate to View Meetings page
    And confirm the meeting
    And Close the browser
    
 Examples:
    |FirstMember|SecondMember|ThirdMember|
    |Chris      |Jim     |Max   |
    

@TestCaseFour
Scenario: Creating a Product
Scenario Outline: To use an Examples table to add products  
	Given Navigate to All -> Products-> Create Product
    When Retrieve information about the product as "<productName>" and "<price>" and "<description>"
    Then Click On Product Save Button
    And Go to the View Products page to see all products listed as "<productName>"
    And Close the browser
    
Examples:
|productName|price|description|
|Selenium|255|Used for test automation|
|Github|305|Defect tracking tool|
|Jira|235|Commonly used defect management tool|