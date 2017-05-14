Feature: Verify the functionality of television landing page

  Background:
    Given I am on television landing page

  Scenario: Verify I can only add maximum 4 tv’s for comparison
    And I have at least 5 televisions listed
    And I have added 4 televisions to comparison
    When I attempt to add an another one
    Then I should see "You already have 4 products for comparison"

  Scenario: Verify filter for brands
    When I filter for "samsung"
    Then I should only see "samsung" televisions

  Scenario: Verify ClearAll button clears the filters
    And I note televisions count
    When I filter for "samsung"
    Then it filters
    When I clearAll the filteThen I should see original television count

  Scenario: Verify sort works for price low to high
    When I sort "Price (low to high)"
    Then I should see televisions sorted from low to high

  Scenario: Verify default sort of Most-recently tested first
    Then I should see "Most-recently tested" selected by default
    And I should see the lastly tested televisions first

#  Scenario: Verify multiple filters
#    And I filter for “samsung”
#    And I filter for “Full HD”
#    Then I should see only samsung Full HD televisions
#
#  Scenario: Show More button shows all available filter options
#    And I click on “Show more” under “Brands”
#    Then I should see all the brands for filtering