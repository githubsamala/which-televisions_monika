Setup instructions:
1. Please have the Maven(version 3.3.9 or later) installed on your machine and have set up environment variables.
2. Clone or Download this git project.
3. On your command prompt navigate to the location where you saved this project and run the command:
            mvn clean verify -Dcucumber.options="--tags @implemented --tags ~@pending"
4. If all tests executes okay you will see the “BUILD SUCCESS” message, Otherwise “BUILD FAILURE” message.
5. Navigate to “{your_project_location}/target/reports/index.html” to see the test results.

Scenarios that are implemented:
Scenario: Verify I can only add maximum 4 TVs for comparison
Scenario Outline: Verify filter for brands
Scenario: Verify ClearAll button clears the filters
Scenario: Verify default sort of Most-recently tested first
Scenario Outline: Verify sort functionality - implemented only for low to high price sort


Scenarios that are unimplemented:
Scenario Outline: Verify Show More button appears if category has more than 6 options
Scenario Outline: Verify page navigation
Scenario Outline: Verify show more button functionality
Scenario Outline: Verify Product count matches with the count shown against the filter option
Scenario: Verify multiple filters
Scenario: Verify price filter

Please Note:
In the feature file, Scenarios that are tagged as “@implemented” are implemented and are passing consistently and scenarios that are tagged as “@pending” are not implemented.