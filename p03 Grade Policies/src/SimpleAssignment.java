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
// Online Sources: Used the SimpleAssignment page
//////////////// (https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2023
//////////////// /p03/javadocs/SimpleAssignment.html) to write the entire class and comments for
//////////////// methods
// Also used Java Point (https://www.javatpoint.com/understanding-toString()-method) to help with
// the toString method in each class
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;

/**
 * This class models a basic assignment with points possible, points earned, and an optional bonus
 * of 5% of possible points.
 * 
 * @author Dylan Zulkosky
 */
public class SimpleAssignment {
  // Indicates whether a 5% bonus is offered (and has not yet been applied) for this assignment
  private boolean bonusAvailable;
  // Indicates whether this assignment has been completed or not
  private boolean isComplete;
  // The number of points possible on this assignment; must be strictly positive
  public final int POINTS_POSSIBLE;
  // The score the student received on this assignment; only set when the assignment is completed
  private double pointsEarned;

  /**
   * Basic constructor, creates an assignment that has not been completed and does not have a bonus
   * available.
   * 
   * @param points - the maximum possible points on the assignment
   */
  public SimpleAssignment(int points) {
    if (points < 1) {
      POINTS_POSSIBLE = 1;
    } else {
      POINTS_POSSIBLE = points;
    }
    isComplete = false;
    bonusAvailable = false;
    pointsEarned = 0;
  }

  /**
   * Overloaded constructor, creates an assignment that has not been completed and could have a
   * bonus available.
   * 
   * @param points - the maximum possible points on the assignment
   * @param bonus  - true if a 5% bonus is available, false otherwise
   */
  public SimpleAssignment(int points, boolean bonus) {
    if (points < 1) {
      POINTS_POSSIBLE = 1;
    } else {
      POINTS_POSSIBLE = points;
    }
    isComplete = false;
    bonusAvailable = bonus;
    pointsEarned = 0;
  }

  /**
   * Returns the number of points earned on the assignment.
   * 
   * @return - the number of points earned on the assignment
   */
  public double getPoints() {
    return pointsEarned;
  }

  /**
   * Reports whether this assignment has been completed yet.
   * 
   * @return - true if this assignment has been completed, false otherwise
   */
  public boolean isComplete() {
    return isComplete;
  }

  /**
   * Completes the assignment and records the provided score.
   * 
   * @param score - the score to record for this assignment. If less than 0, recorded score is 0. If
   *              greater than the number of points possible, recorded score is the number of points
   *              possible.
   */
  public void complete(double score) {
    if (!isComplete) {
      if (score < 0) {
        pointsEarned = 0;
      } else if (score > POINTS_POSSIBLE) {
        pointsEarned = POINTS_POSSIBLE;
      } else {
        pointsEarned = score;
      }
      isComplete = true;
    }
  }

  /**
   * If the assignment has been completed and there is a bonus available, adds 5% of possible points
   * to the earned points total, up to a maximum of the number of possible points.
   */
  public void awardBonus() {
    if (isComplete && bonusAvailable) {
      double bonus = POINTS_POSSIBLE * 0.05; // 5% bonus
      pointsEarned += bonus;
      if (pointsEarned > POINTS_POSSIBLE) {
        pointsEarned = POINTS_POSSIBLE;
      }
      bonusAvailable = false;
    }
  }

  /**
   * Creates random assignment scores with a mean of 80% and a standard deviation of 15%.
   * 
   * @param n        the number of assignment scores to create
   * @param maxScore the maximum score value to create
   * @return an array of the SimpleAssignments created under those requirements
   */
  public static SimpleAssignment[] makeRandomAssignments(int n, int maxScore) {
    Random rand = new Random();
    SimpleAssignment[] result = new SimpleAssignment[n];
    double mean = 0.80;
    double std = 0.15;
    for (int i = 0; i < n; i++) {
      double pctScore = rand.nextGaussian(mean, std);
      result[i] = new SimpleAssignment(maxScore);
      result[i].complete(pctScore * maxScore);
    }
    return result;
  }

  /*
   * Creates and returns a String representation of this SimpleAssignment. This includes the score
   * as a number of points earned out of points possible, or an asterisk out of points possible if
   * the assignment has not yet been completed.
   */
  public String toString() {
    if (isComplete) {
      return pointsEarned + "/" + POINTS_POSSIBLE;
    } else {
      return "*/" + POINTS_POSSIBLE;
    }
  }
}
