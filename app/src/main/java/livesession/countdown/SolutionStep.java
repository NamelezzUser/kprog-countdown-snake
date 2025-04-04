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

package livesession.countdown;

/**
 * Single solution step to solve a Countdown math riddle.
 * This class is designed as immutable (java 11), thus having only a contructor and getters.
 */
public class SolutionStep {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SolutionStep.class);

  private NumberSet numberSet;
  private Operation operation;

  /**
   * Constructor.
   *
   * @param numberSet NumberSet used in the solution step.
   * @param operation Operation used in the solution step.
   */
  public SolutionStep(NumberSet numberSet, Operation operation) {
    this.numberSet = numberSet;
    this.operation = operation;
  }

  public NumberSet getNumberSet() {
    return numberSet;
  }

  public Operation getOperation() {
    return operation;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("SolutionStep{");
    sb.append("numberSet=").append(numberSet);
    sb.append(", operation=").append(operation);
    sb.append('}');
    return sb.toString();
  }
}