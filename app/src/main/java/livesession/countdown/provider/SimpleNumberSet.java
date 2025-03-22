/**
 * This file is part of countdown-snake-java.
 *
 * Copyright (c) 2025 Enes Korkmaz, Mira Wagner and Mae Weiland
 *
 * countdown-snake-java is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License Version 3 as published by
 * the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package livesession.countdown.provider;

import java.util.ArrayList;
import java.util.Arrays;
import livesession.countdown.NumberSet;

/**
 * The type Simple number set.
 */
public class SimpleNumberSet implements NumberSet {
  private ArrayList<Integer> numbers;

  /**
   * Constructor of the class SimpleNumberSet.
   *
   * @param numbers the numbers
   */
  public SimpleNumberSet(ArrayList<Integer> numbers) {
    this.numbers = numbers;
  }

  /**
   * Number of numbers of the set.
   */
  @Override
  public int size() {
    return numbers.size();
  }

  /**
   * returns the numbers as an array of int.
   *
   * @return int array
   */
  @Override
  public int[] getNumbersAsArray() {
    int[] numberArray = new int[numbers.size()];
    for (int i = 0; i < numbers.size(); i++) {
      numberArray[i] = numbers.get(i);
    }
    return numberArray;
  }

  /**
   * Returns the numbers as a ArrayList of Integer. This ArrayList is a list independent of the
   * content of the object producing it.
   *
   * @return a new ArrayList of Integer object
   */
  @Override
  public ArrayList<Integer> getNumbersAsArrayList() {
    return new ArrayList<>(numbers);
  }

  /**
   * Checks if the NumberSet contains the target number.
   *
   * @param target target number to be found
   * @return true if the set contains the number, otherwise false
   */
  @Override
  public boolean contains(int target) {
    return numbers.contains(target);
  }

  /**
   * Returns the value at the given index. The index starts with the value 0.
   *
   * @param index index of the number to be returned
   * @return number on the given index
   */
  @Override
  public int getByIndex(int index) {
    return numbers.get(index);
  }

  @Override
  public String toString() {
    return "SimpleNumberSet{"
        + "numbers=" + Arrays.toString(getNumbersAsArray())
        + '}';
  }
}