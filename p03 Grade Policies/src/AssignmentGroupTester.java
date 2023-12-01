//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Personal Grade Checker For Your Classes
// Course: CS 300 Fall 2023
//
// Author: Dylan Zulkosky
// Email: dzulkosky@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: None
// Partner Email: None
// Partner Lecturer's Name: None
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: I used geeksforgeeks
//////////////// (https://www.geeksforgeeks.org/internal-working-of-arraylist-in-java/)
//////////////// to help with arraylists in this project and how to use them properly
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class tests the functionality of different java classes like ScalingAssignmentGroup,
 * DropAssignmentGroup, and AssignmentGroup to see if the methods work and apply as how they are
 * coded in their own class
 * 
 * @author Dylan Zulkosky
 */
public class AssignmentGroupTester {

  /**
   * This methods tests to see if an assignment can be added
   * 
   * @return true if assignment is added correctly
   */
  public static boolean testAddOneAssignment() {
    AssignmentGroup aGroup1 = new AssignmentGroup(0.1);

    SimpleAssignment assignment = new SimpleAssignment(50);

    aGroup1.addAssignment(assignment);

    SimpleAssignment retrievedAssignment = aGroup1.getAssignment(0);

    // Check if the retrieved assignment is the same as the added assignment
    return retrievedAssignment != null && retrievedAssignment.equals(assignment);
  }

  /**
   * This method checks to see if you can add multiple assignments at once
   * 
   * @return true if assignments added are added correctly
   */
  public static boolean testAddManyAssignments() {
    // Create an AssignmentGroup
    AssignmentGroup aGroup1 = new AssignmentGroup(0.2);

    // Create a new array of SimpleAssignment objects
    SimpleAssignment[] p1 = new SimpleAssignment[3];
    p1[0] = new SimpleAssignment(50);
    p1[1] = new SimpleAssignment(75);
    p1[2] = new SimpleAssignment(60);

    // Add the assignments to the AssignmentGroup
    for (int i = 0; i < p1.length; i++) {
      aGroup1.addAssignment(p1[i]);
    }

    // Verify that the assignments were added correctly
    boolean passedTest = true;
    for (int i = 0; i < p1.length; i++) {
      SimpleAssignment retrievedAssignment = aGroup1.getAssignment(i);
      if (retrievedAssignment == null || !retrievedAssignment.equals(p1[i])) {
        passedTest = false;
        break;
      }
    }

    return passedTest;
  }

  // PROVIDED
  // Verify that the getTotalPossible() method returns the expected value
  // in EACH of the classes which implements the method
  public static boolean testGetTotal() {
    boolean result = testGroupTotal();
    result &= testDropTotal();
    result &= testScaledTotal();

    return result;
  }

  /**
   * This method checks to see if it can calculate the total points possible for a group of
   * assignments
   * 
   * @return true if total is equal to the assignments total
   */
  public static boolean testGroupTotal() {
    // Create an AssignmentGroup
    AssignmentGroup aGroup1 = new AssignmentGroup(0.2);

    // Create a new array of SimpleAssignment objects with different possible points
    SimpleAssignment[] a2 = new SimpleAssignment[3];
    a2[0] = new SimpleAssignment(30);
    a2[1] = new SimpleAssignment(40);
    a2[2] = new SimpleAssignment(50);

    // Add the assignments to the AssignmentGroup
    for (int i = 0; i < a2.length; i++) {
      aGroup1.addAssignment(a2[i]);
    }

    // Calculate the expected total possible points
    int expectedTotalPossible = 30 + 40 + 50;

    // Verify that getTotalPossible() returns the correct total possible points
    return aGroup1.getTotalPossible() == expectedTotalPossible;
  }

  /**
   * This method tests to see what the total is after dropping the 2 lowest score assignments
   * 
   * @return true if tests pass and points equal the expected, otherwise false
   */
  private static boolean testDropTotal() {
    // Create a new sample DropAssignmentGroup with 2 drops
    DropAssignmentGroup group = new DropAssignmentGroup(0.2, 2);

    // Create a new sample SimpleAssignments with scores and add them to the group
    SimpleAssignment p1 = new SimpleAssignment(100);
    SimpleAssignment p2 = new SimpleAssignment(50);
    SimpleAssignment p3 = new SimpleAssignment(75);

    // Complete the assignments with scores
    p1.complete(95);
    p2.complete(40);
    p3.complete(70);

    // Add the completed assignments to the group
    group.addAssignment(p1);
    group.addAssignment(p2);
    group.addAssignment(p3);

    // Calculate the expected total possible points after dropping the lowest 2 actual score, not
    // percent
    int expectedTotalPossible = p1.POINTS_POSSIBLE;

    // Get the actual total possible points using getTotalPossible()
    int actualTotalPossible = group.getTotalPossible();

    // Compare the expected and actual values
    return expectedTotalPossible == actualTotalPossible;
  }

  /**
   * This method checks to see if the scaled factor of some assignments equal the total possible
   * points
   * 
   * @return true if both points equal each other after tests
   */
  private static boolean testScaledTotal() {
    // Create a sample ScalingAssignmentGroup with known assignments and scaling factor
    ScalingAssignmentGroup group = new ScalingAssignmentGroup(0.8, 0.8);

    // Create sample SimpleAssignments and add them to the group
    SimpleAssignment p1 = new SimpleAssignment(100);
    SimpleAssignment p2 = new SimpleAssignment(50);
    group.addAssignment(p1);
    group.addAssignment(p2);

    // Calculate the expected total possible points with scaling factor
    double expectedTotalPossible = (p1.POINTS_POSSIBLE + p2.POINTS_POSSIBLE) * 0.8;

    // Get the actual total possible points using getTotalPossible()
    double actualTotalPossible = group.getTotalPossible();

    // Compare the expected and actual values
    return expectedTotalPossible == actualTotalPossible;
  }

  // PROVIDED
  // Verify that the getPoints() method returns the expected value in EACH
  // of the classes which implements the method
  public static boolean testGetPoints() {
    boolean result = testGroupPoints();
    result &= testDropPoints();
    result &= testScaledPoints();

    return result;
  }

  /**
   * This method tests to see what the total expected points from a group of assignments are
   * 
   * @return true if the expected total equals the actual total
   */
  public static boolean testGroupPoints() {
    // Create an AssignmentGroup with 30%
    AssignmentGroup group = new AssignmentGroup(0.3);

    // Create an array of SimpleAssignment objects with different earned points
    SimpleAssignment[] a1 = new SimpleAssignment[4];
    a1[0] = new SimpleAssignment(50);
    a1[0].complete(40);

    a1[1] = new SimpleAssignment(75);
    a1[1].complete(70);

    a1[2] = new SimpleAssignment(60);
    a1[2].complete(55);

    a1[3] = new SimpleAssignment(70);
    a1[3].complete(65);

    // Add the assignments to the AssignmentGroup
    for (int i = 0; i < a1.length; i++) {
      group.addAssignment(a1[i]);
    }

    // Get the expected total earned points
    double expectedTotalPoints = 40 + 70 + 55 + 65;

    // Verify that getPoints() returns the correct total earned points
    return group.getPoints() == expectedTotalPoints;
  }

  /**
   * This method tests to see if the lowest 2 points for some assignments get dropped (not by
   * percent)
   * 
   * @return true if the left over assignment equals total points
   */
  private static boolean testDropPoints() {
    // Create a new DropAssignmentGroup
    DropAssignmentGroup group1 = new DropAssignmentGroup(0.2, 2);

    // Create some sample assignments
    SimpleAssignment a1 = new SimpleAssignment(100);
    SimpleAssignment a2 = new SimpleAssignment(50);
    SimpleAssignment a3 = new SimpleAssignment(75);

    // Complete the new assignments with random scores
    a1.complete(95);
    a2.complete(40);
    a3.complete(70);

    // Add the new assignments to the group
    group1.addAssignment(a1);
    group1.addAssignment(a2);
    group1.addAssignment(a3);

    // Calculate the expected result manually (dropping the lowest 2 total score not percent)
    double expectedResult = a1.getPoints();

    // Get the result using getPoints() method
    double actualResult = group1.getPoints();

    // Compare the actual result with the expected result
    return actualResult == expectedResult;
  }

  /**
   * This method tests to see the points of some assignments after a scaling factor equal each other
   * 
   * @return true if both total points equal each other
   */
  private static boolean testScaledPoints() {
    // Create a new sample ScalingAssignmentGroup with known assignments and scaling factor
    ScalingAssignmentGroup g1 = new ScalingAssignmentGroup(0.8, 0.8);

    // Create new sample SimpleAssignments and add them to the group
    SimpleAssignment p1 = new SimpleAssignment(100);
    SimpleAssignment p2 = new SimpleAssignment(50);
    g1.addAssignment(p1);
    g1.addAssignment(p2);

    // Get the expected total points
    double expectedTotalPoints = (p1.getPoints() + p2.getPoints()) * 0.8;

    // Get the actual total points using getPoints()
    double actualTotalPoints = g1.getPoints();

    // Compare the expected and actual values
    return expectedTotalPoints == actualTotalPoints;
  }

  /**
   * This method tests to see if all assignments are completed with valid scores
   * 
   * @return true if all assignments are properly completed
   */
  public static boolean testComplete() {
    // Create an AssignmentGroup with 20%
    AssignmentGroup g2 = new AssignmentGroup(0.2);

    // Create a new array of SimpleAssignment objects with different completion scores
    SimpleAssignment d1 = new SimpleAssignment(50);
    d1.complete(40);

    SimpleAssignment d2 = new SimpleAssignment(75);
    d2.complete(75);

    SimpleAssignment d3 = new SimpleAssignment(60);
    d3.complete(60);

    // Add the new assignments to the AssignmentGroup
    g2.addAssignment(d1);
    g2.addAssignment(d2);
    g2.addAssignment(d3);

    // Check that isComplete() returns true for all assignments in the group
    return g2.isComplete();
  }

  /**
   * This method runs all the tests for the tester methods created above
   * 
   * @param args - never used
   */
  public static void main(String[] args) {
    if (SimpleAssignmentTester.runAllTests()) {
      System.out.println("add one: " + (testAddOneAssignment() ? "PASS" : "fail"));
      System.out.println("add many: " + (testAddManyAssignments() ? "PASS" : "fail"));
      System.out.println("get total: " + (testGetTotal() ? "PASS" : "fail"));
      System.out.println("get points: " + (testGetPoints() ? "PASS" : "fail"));
      System.out.println("complete: " + (testComplete() ? "PASS" : "fail"));
    } else {
      System.out.println("Your SimpleAssignment implementation does NOT pass its tests!\n"
          + "Do NOT continue with AssignmentGro until you have passed all SimpleAssignment tests.");
    }
  }

}
