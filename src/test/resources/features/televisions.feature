Feature: Verify the functionality of television landing page

  Background:
    Given I am on television landing page

  @implemented
  Scenario: Verify I can only add maximum 4 tv’s for comparison
    And I have at least 5 televisions listed
    And I have added 4 televisions to comparison
    When I attempt to add an another one
    Then I should see "You already have 4 products for comparison"

  @implemented
  Scenario Outline: Verify filter for brands
    When I filter for "<brand>"
    Then I should only see "<brand>" televisions
    Examples:
      | brand |
      | sony  |
      | lg    |

  @implemented
  Scenario: Verify ClearAll button clears the filters
    And I note televisions count
    When I filter for "samsung"
    Then it filters
    When I clearAll the filter
    Then I should see original television count

  @implemented
  Scenario: Verify default sort of Most-recently tested first
    Then I should see "Most-recently tested" selected by default
    And I should see the lastly tested televisions first

  @implemented
  Scenario Outline: Verify sort functionality
    When I sort "<dropdown_option>"
    Then I should see televisions sorted accordingly
    Examples:
      | dropdown_option     |
      | Price (low to high) |

    # Below scenarios is a replica of the above one with different dropdown values which I have not implemented.

  @pending
  Scenario Outline: Verify sort functionality
    When I sort "<dropdown_option>"
    Then I should see televisions sorted accordingly
    Examples:
      | dropdown_option           |
      | Most-recently tested      |
      | Price (high to low)       |
      | Screen size (high to low) |
      | Most-recently launched    |

  @pending
  Scenario Outline: Verify Show More button appears if category has more than 6 options
    When I verify number of filter options under "<category>" is more than 6
    Then I should see show more button appears
    Examples:
      | category             |
      | screen size          |
      | brands               |
      | popular screen sizes |
      | screen type          |
      | resolution           |
      | features             |
      | retailers            |

  @pending
  Scenario Outline: Verify page navigation
    When I click on "<button>"
    Then I should navigate to corresponding page
    Examples:
      | button   |
      | next     |
      | last     |
      | previous |
      | first    |

  @pending
  Scenario Outline: Verify show more button functionality
    When I click on show more button under "<category>"
    Then it reveals all the filter options
    Examples:
      | category    |
      | screen size |
      | brands      |
      | retailers   |

  @pending
  Scenario Outline: Verify Product count matches with the count shown against the filter option
    And I note the count against the "<filter_option>"
    When I filter for "<filter_option>"
    Then total number of products should match with the count noted earlier
    Examples:
      | filter_option |
      | Smart TV      |
      | John Lewis    |
      | 4K ultra HD   |

  @pending
  Scenario: Verify multiple filters
    When I filter for “samsung”
    And I filter for “Full HD”
    Then I should see only samsung Full HD televisions

  @pending
  Scenario: Verify price filter
    When I filter for television prices between 1200 to 1600
    Then I should only see televisions between that range
