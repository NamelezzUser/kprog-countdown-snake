package livesession.countdown;

import java.util.ArrayList;

/**
 * Set of numbers to be used for countdown riddle.
 *
 * <p>The number of numbers may vary, depending on the number of calculation steps already done.
 */
public interface NumberSet {

  /**
   * Number of numbers of the set.
   */
  int size();

  /**
   * returns the numbers as an array of int.
   *
   * @return int array
   */
  int[] getNumbersAsArray();

  /**
   * Returns the numbers as a ArrayList of Integer. This ArrayList is a list independent of the
   * content of the object producing it.
   *
   * @return a new ArrayList of Integer object
   */
  ArrayList<Integer> getNumbersAsArrayList();

  /**
   * Checks if the NumberSet contains the target number.
   *
   * @param target target number to be found
   * @return true if the set contains the number, otherwise false
   */
  boolean contains(int target);

  /**
   * Returns the value at the given index. The index starts with the value 0.
   *
   * @param index index of the number to be returned
   * @return number on the given index
   */
  int getByIndex(int index);
}