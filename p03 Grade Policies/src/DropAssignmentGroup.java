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
// Online Sources: Used the DropAssignmentGroup page
//////////////// (https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2023/p03/javadocs
//////////////// /DropAssignmentGroup.html) to help create the entire class and comments for methods
//// Also used Java Point (https://www.javatpoint.com/understanding-toString()-method) to help with
// the toString method in each class
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * This class models a collection of scores which are worth a given percentage of a student's grade.
 * When calculating the point values for this type of assignment group, the lowest N scores are
 * dropped, which is facilitated using a pair of static utility methods.
 * 
 * @author Dylan Zulkosky
 */
public class DropAssignmentGroup {
  private ArrayList<SimpleAssignment> assignments;
  private int numDropped;
  public final double PERCENT_OF_TOTAL;

  /**
   * Basic constructor, initializes an assignment group which is worth the given percentage of a
   * student's grade and which allows a given number of dropped scores. This method also sets up
   * this object's ArrayList to accept new SimpleAssignments.
   * 
   * @param percent - the percent of the total grade that this assignment group represents, assumed
   *                to be between 0 and 1.
   * @param drops   - the number of assignments to be dropped; if the given number is less than 1,
   *                the constructor will set it to 1.
   */
  public DropAssignmentGroup(double percent, int drops) {
    if (percent < 0 || percent > 1.0) {
      throw new IllegalArgumentException("Percent must be between 0 and 1");
    }
    PERCENT_OF_TOTAL = percent;

    if (drops < 1) {
      this.numDropped = 1;
    } else {
      this.numDropped = drops;
    }

    assignments = new ArrayList<>();
  }

  /**
   * Accesses the total number of earned points across all assignments in this DropAssignmentGroup,
   * after dropping the lowest N (numDropped)
   * 
   * @return the sum of all earned points of all non-dropped assignments in this DropAssignmentGroup
   */
  public double getPoints() {
    ArrayList<SimpleAssignment> nonDroppedAssignments = dropLowestN(assignments, numDropped);
    double totalPoints = 0.0;
    int numAssignments = nonDroppedAssignments.size();
    for (int i = 0; i < numAssignments; i++) {
      SimpleAssignment assignment = nonDroppedAssignments.get(i);
      totalPoints += assignment.getPoints();
    }
    return totalPoints;
  }

  /**
   * Accesses the total number of points possible across all assignments in this
   * DropAssignmentGroup, after dropping the lowest N (numDropped). Be careful - not all assignments
   * in this group are required to have the same number of points possible.
   * 
   * @return the sum of all possible points of all non-dropped assignments in this
   *         DropAssignmentGroup
   */
  public int getTotalPossible() {
    ArrayList<SimpleAssignment> nonDroppedAssignments = dropLowestN(assignments, numDropped);
    int totalPossible = 0;
    int numAssignments = nonDroppedAssignments.size();
    for (int i = 0; i < numAssignments; i++) {
      SimpleAssignment assignment = nonDroppedAssignments.get(i);
      totalPossible += assignment.POINTS_POSSIBLE;
    }
    return totalPossible;
  }

  /**
   * Determines whether all assignments currently in this DropAssignmentGroup have been completed,
   * after dropping the lowest N (numDropped).
   * 
   * @return true if ALL non-dropped assignments in this DropAssignmentGroup have been completed;
   *         false otherwise
   */
  public boolean isComplete() {
    ArrayList<SimpleAssignment> nonDroppedAssignments = dropLowestN(assignments, numDropped);

    for (int i = 0; i < nonDroppedAssignments.size(); i++) {
      SimpleAssignment assignment = nonDroppedAssignments.get(i);
      if (!assignment.isComplete()) {
        return false;
      }
    }

    return true;
  }

  /**
   * Accesses the number of assignments currently stored in this DropAssignmentGroup, NOT accounting
   * for drops
   * 
   * @return the number of assignments present in this DropAssignmentGroup
   */
  public int getNumAssignments() {
    return assignments.size();
  }

  /**
   * Retrieves an assignment at the given index in the DropAssignmentGroup, NOT accounting for drops
   * 
   * @param i - the index of the assignment to access (0-based)
   * @return the assignment at the given index, or null if the index is out of bounds
   */
  public SimpleAssignment getAssignment(int i) {
    if (i >= 0 && i < assignments.size()) {
      return assignments.get(i);
    }
    return null;
  }

  /**
   * Adds a single assignment object to this DropAssignmentGroup
   * 
   * @param assignment - the SimpleAssignment to add
   */
  public void addAssignment(SimpleAssignment assignment) {
    assignments.add(assignment);
  }

  /**
   * Adds a batch of assignments to this DropAssignmentGroup in the order they appear
   * 
   * @param assignmentBatch - a perfect-size array of SimpleAssignments to add; you may assume there
   *                        are no null values present in this array
   */
  public void addAssignments(SimpleAssignment[] assignmentBatch) {
    for (int i = 0; i < assignmentBatch.length; i++) {
      SimpleAssignment assignment = assignmentBatch[i];
      assignments.add(assignment);
    }
  }

  /**
   * Without modifying the input ArrayList, creates a NEW ArrayList which contains all but the
   * lowest- scoring N (numDropped) assignments from the input ArrayList. If the input ArrayList
   * contains N (numDropped) or fewer assignments, the returned ArrayList will be empty.
   * 
   * @param assignments - an ArrayList containing all assignments
   * @param n           - the number of assignments to drop
   * @return a COPY of the input ArrayList which contains all but the lowest-scoring n (NOT
   *         numDropped) assignments
   */
  public static ArrayList<SimpleAssignment> dropLowestN(ArrayList<SimpleAssignment> assignments,
      int n) {
    if (n <= 0 || assignments.size() <= n) {
      return new ArrayList<>();
    }

    ArrayList<SimpleAssignment> copy = new ArrayList<>(assignments);
    for (int i = 0; i < n; i++) {
      int lowestIndex = getLowestScoreIndex(copy);
      copy.remove(lowestIndex);
    }
    return copy;
  }

  /**
   * Finds the index of the lowest scoring assignment in the given ArrayList. In the case of ties,
   * this method should prefer the assignment with the lower index. No other form of tie-breaking
   * (e.g. points possible, completeness, etc) should be implemented.
   * 
   * @param assignments - an ArrayList containing the assignments to analyze
   * @return the index (0-based) of the lowest scoring assignment
   */
  public static int getLowestScoreIndex(ArrayList<SimpleAssignment> assignments) {
    if (assignments.isEmpty()) {
      throw new IllegalArgumentException("Assignments list is empty");
    }

    double lowestScore = Double.MAX_VALUE;
    int lowestIndex = 0;

    for (int i = 0; i < assignments.size(); i++) {
      SimpleAssignment assignment = assignments.get(i);
      if (assignment.getPoints() < lowestScore) {
        lowestScore = assignment.getPoints();
        lowestIndex = i;
      }
    }

    return lowestIndex;
  }

  /**
   * Creates a String representation of this DropAssignmentGroup. Each assignment is listed by
   * number (1-based) and its String representation.
   */
  public String toString() {
    ArrayList<SimpleAssignment> nonDroppedAssignments = dropLowestN(assignments, numDropped);
    int assignmentNumber = 1;
    int numAssignments = nonDroppedAssignments.size();
    String result = "";

    for (int i = 0; i < numAssignments; i++) {
      SimpleAssignment assignment = nonDroppedAssignments.get(i);
      result += "Assignment " + assignmentNumber + ": " + assignment.toString() + "\n";
      assignmentNumber++;
    }

    return result;
  }
}
