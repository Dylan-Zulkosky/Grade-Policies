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
// Online Sources: Used the AssignmentGroup page
//////////////// (https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2023/p03
//////////////// /javadocs/AssignmentGroup.html) to help create the entire class and comments for
//////////////// methods
// Also used Java Point (https://www.javatpoint.com/understanding-toString()-method) to help with
// the toString method in each class
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * This class models a collection of scores which are worth a given percentage of a student's grade.
 * No adjustments are made to these scores.
 * 
 * @author Dylan Zulkosky
 */
public class AssignmentGroup {
  // An ArrayList containing the assignments associated with this AssignmentGroup
  private ArrayList<SimpleAssignment> assignments;
  // The percent of the total grade this AssignmentGroup comprises
  public final double PERCENT_OF_TOTAL;

  /**
   * Basic constructor, initializes an assignment group which is worth the given percentage of a
   * student's grade.
   * 
   * @param percent - the percent of the total grade that this assignment group represents, assumed
   *                to be between 0 and 1.
   */
  public AssignmentGroup(double percent) {
    this.PERCENT_OF_TOTAL = percent;
    this.assignments = new ArrayList<>();
  }

  /**
   * Adds a single assignment object to this AssignmentGroup
   * 
   * @param assignment - the SimpleAssignment to add
   */
  public void addAssignment(SimpleAssignment assignment) {
    assignments.add(assignment);
  }

  /**
   * Adds a batch of assignments to this AssignmentGroup in the order they appear
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
   * Retrieves an assignment at the given index in the AssignmentGroup
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
   * Accesses the number of assignments currently stored in this AssignmentGroup
   * 
   * @return the number of assignments present in this AssignmentGroup
   */
  public int getNumAssignments() {
    return assignments.size();
  }


  /**
   * Accesses the total number of earned points across all assignments in this AssignmentGroup
   * 
   * @return the sum of all earned points of all assignments in this AssignmentGroup
   */
  public double getPoints() {
    double totalPoints = 0.0;
    int numAssignments = assignments.size();
    for (int i = 0; i < numAssignments; i++) {
      SimpleAssignment assignment = assignments.get(i);
      totalPoints += assignment.getPoints();
    }
    return totalPoints;
  }

  /**
   * Accesses the total number of points possible across all assignments in this AssignmentGroup. Be
   * careful - not all assignments in this group are required to have the same number of points
   * possible.
   * 
   * @return the sum of all possible points of all assignments in this AssignmentGroup
   */
  public int getTotalPossible() {
    int totalPossible = 0;
    int numAssignments = assignments.size();
    for (int i = 0; i < numAssignments; i++) {
      SimpleAssignment assignment = assignments.get(i);
      totalPossible += assignment.POINTS_POSSIBLE;
    }
    return totalPossible;
  }

  /**
   * Determines whether all assignments currently in this AssignmentGroup have been completed.
   * 
   * @return true if ALL assignments in this AssignmentGroup have been completed; false otherwise
   */
  public boolean isComplete() {
    int numAssignments = assignments.size();
    for (int i = 0; i < numAssignments; i++) {
      SimpleAssignment assignment = assignments.get(i);
      if (!assignment.isComplete()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Creates a String representation of this AssignmentGroup. Each assignment is listed by number
   * (1-based) and its String representation.
   */
  public String toString() {
    String result = "";
    int assignmentNumber = 1;
    int numAssignments = assignments.size();
    for (int i = 0; i < numAssignments; i++) {
      SimpleAssignment assignment = assignments.get(i);
      result += "Assignment " + assignmentNumber + ": " + assignment.toString() + "\n";
      assignmentNumber++;
    }
    return result;
  }
}
