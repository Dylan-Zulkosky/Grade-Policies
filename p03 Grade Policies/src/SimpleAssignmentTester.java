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

/*
 * This class tests all of the compenets created in SimpleAssignment to see if they work properly
 * based on how they were created
 * 
 * @author Dylan Zulkosky
 */
public class SimpleAssignmentTester {

  /**
   * This method creates 2 assignments and checks to see if the score that is entered equals the
   * score that should be returned from running the methods from SimpleAssignment
   * 
   * @return true if the tests passed, false otherwise
   */
  public static boolean testAccessors() {
    SimpleAssignment a1 = new SimpleAssignment(50); // 50 pints
    SimpleAssignment a2 = new SimpleAssignment(100); // 100 points

    // Check initial values
    boolean testPassed = a1.getPoints() == 0 && a2.getPoints() == 0 && !a1.isComplete()
        && !a2.isComplete() && a1.POINTS_POSSIBLE == 50 && a2.POINTS_POSSIBLE == 100;

    // Complete the assignments with different grades
    a1.complete(40);
    a2.complete(90);

    // Check values after completion
    testPassed &= a1.getPoints() == 40 && a2.getPoints() == 90 && a1.isComplete() && a2.isComplete()
        && a1.POINTS_POSSIBLE == 50 && a2.POINTS_POSSIBLE == 100;

    return testPassed;
  }

  // PROVIDED
  // This method calls a number of shorter helper methods, which test various cases for a
  // SimpleAssignment with the bonus option available
  // By breaking these out into shorter tests, you can use the debugger to step through this
  // method and quickly determine which of the tests are failing, if any.
  public static boolean testSimpleBonus() {
    boolean result = testAwardBonus();
    result &= testBonusTwice();
    result &= testNoBonus();
    result &= testBonus105();

    return result;
  }

  /**
   * This method tests to see if a bonus for an assignment gets added to a completed assignment
   * 
   * @return true if code worked as it should
   */
  private static boolean testAwardBonus() {
    // Create a SimpleAssignment object with a score less than 95%
    SimpleAssignment p1 = new SimpleAssignment(100, true);
    p1.complete(80); // Score is 80 percent which is less than 95%

    // Check initial values
    boolean passedTest = p1.getPoints() == 80 && p1.isComplete() && p1.POINTS_POSSIBLE == 100;

    // Call awardBonus()
    p1.awardBonus();

    // Check values after awarding the bonus
    passedTest &= p1.getPoints() == 85; // 5 point bonus

    return passedTest;
  }

  /**
   * This method checks to make sure a bonus cannot be apllied to the same assignment twice
   * 
   * @return true if bonus is only applied once
   */
  private static boolean testBonusTwice() {
    // Create a new SimpleAssignment object
    SimpleAssignment a2 = new SimpleAssignment(100, true);
    a2.complete(80);

    // Call awardBonus() once
    a2.awardBonus();

    // Store the points after the first bonus
    double pointsAfterFirstBonus = a2.getPoints();

    // Call awardBonus() again
    a2.awardBonus();

    // Check if points remain the same after the second bonus
    return a2.getPoints() == pointsAfterFirstBonus;
  }

  /**
   * This method checks to make sure that the bonus is not applied to an assignment
   * 
   * @return true if bonus is not applied
   */
  private static boolean testNoBonus() {
    SimpleAssignment a3 = new SimpleAssignment(100, false);
    a3.complete(80);

    // Call awardBonus()
    a3.awardBonus();

    // Check to make sure points do not change after calling awardBonus
    return a3.getPoints() == 80;
  }

  /**
   * This method checks to make sure the score of an assignment with a bonus cannot exceed 100
   * 
   * @return true if the assignment does not exceed 100 after bonus, false otherwise
   */
  private static boolean testBonus105() {
    SimpleAssignment p2 = new SimpleAssignment(100, true);
    p2.complete(97);

    // Call awardBonus()
    p2.awardBonus();

    // Check if points do not exceed total possible points
    return p2.getPoints() <= 100;
  }

  // PROVIDED
  // This method calls a number of shorter helper methods, all of which test error cases
  // in the SimpleAssignment class.
  // By breaking these out into shorter tests, you can use the debugger to step through this
  // method and quickly determine which of the tests are failing, if any.
  public static boolean testSimpleErrorCases() {
    boolean result = testBadConstructorInput();
    result &= testBonusIncomplete();
    result &= testBadPointsEarned();
    result &= testCompleteAssignmentTwice();

    return result;
  }

  /**
   * This method checks to make sure that it is using possible points in the valid range for an 
   * assignment
   * 
   * @return returns true if exceptions are thrown, false otherwise
   */
  private static boolean testBadConstructorInput() {
    boolean passedTest = true;

    // Attempt to create a SimpleAssignment object with negative points
    SimpleAssignment g1 = new SimpleAssignment(-10);
    if (g1.POINTS_POSSIBLE != 1) {
        passedTest = false;
    }

    // Attempt to create a SimpleAssignment object with zero points
    SimpleAssignment g2 = new SimpleAssignment(0);
    if (g2.POINTS_POSSIBLE != 1) {
        passedTest = false;
    }

    // Attempt to create a SimpleAssignment object with negative points and a bonus
    SimpleAssignment g3 = new SimpleAssignment(-5, true);
    if (g3.POINTS_POSSIBLE != 1) {
        passedTest = false;
    }

    // Attempt to create a SimpleAssignment object with zero points possible and a bonus
    SimpleAssignment g4 = new SimpleAssignment(0, true);
    if (g4.POINTS_POSSIBLE != 1) {
        passedTest = false;
    }

    return passedTest;
}

  /**
   * This method checks to make sure no award can be applied to a incomplete assignment 
   * 
   * @return true if tests pass
   */
  private static boolean testBonusIncomplete() {
    SimpleAssignment p3 = new SimpleAssignment(100, true);

    // Call awardBonus() on an incomplete assignment
    p3.awardBonus();

    // Check if points remain unchanged (should still be 0)
    return p3.getPoints() == 0;
  }

  /**
   * This method tests to see if the assignment is completed with a score not in possible range
   * 
   * @return true is methods work and tests pass
   */
  private static boolean testBadPointsEarned() {
    // Create a SimpleAssignment object with 100 points possible
    SimpleAssignment a5 = new SimpleAssignment(100, true);

    // Attempt to complete the assignment with a negative score
    a5.complete(-10);

    // Check that points earned are set to 0 for negative score input
    boolean passedTest = a5.getPoints() == 0;

    // Attempt to complete the assignment with points earned greater than points possible
    a5.complete(110);

    // Check to make sure points earned at stopped at 100
    if (a5.getPoints() == 100 || a5.getPoints() == 0) {
      passedTest &= true;
    } else {
      passedTest &= false;
    }

    return passedTest;
  }

  /**
   * This method tests to see if an assignment can be completed twice
   * 
   * @return true if points stay the same
   */
  private static boolean testCompleteAssignmentTwice() {
    SimpleAssignment b1 = new SimpleAssignment(100, true);

    // Call complete() with a score of 80
    b1.complete(80);

    // Store the points after the first completion
    double pointsAfterFirstComplete = b1.getPoints();

    // Call complete() again with a different score
    b1.complete(90);

    // Check if points remain the same after the second completion
    return b1.getPoints() == pointsAfterFirstComplete;
  }

  // PROVIDED
  // This method reports whether all provided SimpleAssignmentTester methods
  // have passed.
  public static boolean runAllTests() {
    return testAccessors() && testSimpleBonus() && testSimpleErrorCases();
  }

  public static void main(String[] args) {
    System.out.println("basic: " + (testAccessors() ? "PASS" : "fail"));
    System.out.println("bonus: " + (testSimpleBonus() ? "PASS" : "fail"));
    System.out.println("edge cases: " + (testSimpleErrorCases() ? "PASS" : "fail"));
  }

}
