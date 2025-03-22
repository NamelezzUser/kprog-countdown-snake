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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import livesession.countdown.Solution;
import livesession.countdown.SolutionStep;

/**
 * The type Simple solution.
 */
public class DequeSolution implements Solution {

  private Deque<SolutionStep> stepDeque;

  public DequeSolution() {
    stepDeque = new ArrayDeque<>();
  }

  @Override
  public int size() {
    return stepDeque.size();
  }

  @Override
  public Iterator<SolutionStep> iterator() {
    return stepDeque.iterator();
  }

  /**
   * Adds a solution step to the deque.
   *
   * @param step solution step to be added
   */
  public void push(SolutionStep step) {
    stepDeque.push(step);
  }

  /**
   * Removes and returns the last added solution step from the deque.
   *
   * @return last added solution step
   */
  public SolutionStep pop() {
    return stepDeque.pop();
  }

  /**
   * Gets the immutable solution.
   *
   * @return the immutable solution
   */
  public ImmutableSolution getImmutableSolution() {
    return new ImmutableSolution(new ArrayList<>(stepDeque));
  }
}