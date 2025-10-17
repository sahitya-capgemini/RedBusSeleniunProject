@bookTrains
Feature: Train booking using the search feature
  These scenarios validate train search functionality with various conditions.

  # Common setup for all scenarios
  Background:
    Given the user is on the RedBus Railways page

  @positiveTrainSearch
  Scenario Outline: Search trains with valid source and destination, filter by 2AC and time range
    # User enters valid source and destination
    When the user enters source as <sheetNo> and destination as <rowNo>
    And selects the date of travel
    And clicks on the search button
    Then the user is navigated to the results page

    # Apply filters for class and time
    When the user filters for class 2AC

    # Check availability and capture result
    Then the system should display the availability status for the first train
    #And a screenshot of the result should be captured--->after 

  Examples:
| sheetNo | rowNo |
|0|1|
|0|2|


 @negativeTrainSearch
  Scenario Outline: Search trains with missing or invalid source and destination
    # User enters invalid or missing station names
    When the user enters source as <sheetNo> and destination as <rowNo>
    And selects the date of travel
    And clicks on the search button

    # Expect user to remain on same page with error
    Then the user should remain on the same page
    And an error message should be displayed indicating invalid station input

  Examples:
| sheetNo | rowNo |
|1|1|
|1|2|

@sortAndFilterTrains
Scenario Outline: Search and filter trains from Delhi to Mumbai with various filters and sorting
   # User enters valid source and destination
    When the user enters source as <sheetNo> and destination as <rowNo>
    And selects the date of travel
    And clicks on the search button
    Then the user is navigated to the results page
    
     # Apply filters for class and time
    When the user filters for class 3AC
    And the user clears all filters
    And the user changes the quota to Ladies
    And the user sorts the results by Departure Time
    #And the user clicks on the first available seat to book
    Then the results should be sorted in ascending order

  Examples:
| sheetNo | rowNo |
|0|1|
|0|2|

@addPassenger
  Scenario Outline: Go to Passenger details page after searching for trains
# User enters valid source and destination
    When the user enters source as <sheetNo> and destination as <rowNo>
    And selects the date of travel
    And clicks on the search button
    Then the user is navigated to the results page
    
     # Apply filters for class
    When the user filters for class 3AC
    #Check if seat is available and passenger page is navigated or not
    And the user clicks on the first available seat to book if available
    Then the user should be navigated to the passenger details page
  Examples:
| sheetNo | rowNo |
|0|1|
|0|2|

