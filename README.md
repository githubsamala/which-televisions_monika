## Setup instructions:
1. Please have the Maven(version 3.3.9 or later) installed on your machine and have set up environment variables.
2. Clone or Download this git project.
3. On your command prompt navigate to the location where you saved this project and run the command:                  
         ##   mvn clean verify -Dcucumber.options="--tags @implemented --tags ~@pending"
4. If all tests executes okay you will see the “BUILD SUCCESS” message, Otherwise “BUILD FAILURE” message.
5. Navigate to “{your_project_location}/target/reports/index.html” to see the test results.

## Scenarios that are implemented:
1. Scenario: Verify I can only add maximum 4 TVs for comparison                                                         
2. Scenario Outline: Verify filter for brands                                                                           
3. Scenario: Verify ClearAll button clears the filters                                                                  
4. Scenario: Verify default sort of Most-recently tested first                                                                      
5. Scenario Outline: Verify sort functionality - implemented only for low to high price sort                


## Scenarios that are unimplemented:
1. Scenario Outline: Verify Show More button appears if category has more than 6 options                    
2. Scenario Outline: Verify page navigation                                                     
3. Scenario Outline: Verify show more button functionality                                      
4. Scenario Outline: Verify Product count matches with the count shown against the filter option                                    
5. Scenario: Verify multiple filters                                                                        
6. Scenario: Verify price filter                                                    

## Please Note:
In the feature file, Scenarios that are tagged as “@implemented” are implemented and are passing consistently and scenarios that are tagged as “@pending” are not implemented.
