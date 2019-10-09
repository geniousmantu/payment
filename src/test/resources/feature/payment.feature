#Author: mantu.kumar@publicissapient.com
#Keywords Summary : SapientBank decided to rewrite it banking system with new Tech Stack and architecture to become ready for Future of Banking
Feature: Sapient Future Bank for Payment

  Scenario: Get Top Ten Transaction Details
    Given As a bank end user i want to see the top ten Transaction
    When I requested to see top ten transaction
    Then I should be able to see the list of top ten transaction
  