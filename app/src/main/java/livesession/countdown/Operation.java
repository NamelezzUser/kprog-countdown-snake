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

import java.util.StringJoiner;

/**
 * Operation identifying operator and operands.
 */
public class Operation {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Operation.class);
  private Operator operator;
  private int firstIndex;
  private int secondIndex;

  /**
   * Constructor.
   *
   * @param operator    operator used in the operation
   * @param firstIndex  index of the first element of the operation
   * @param secondIndex index of the second element of the operation
   */
  public Operation(final Operator operator, final int firstIndex, final int secondIndex) {
    this.operator = operator;
    this.firstIndex = firstIndex;
    this.secondIndex = secondIndex;
  }

  public Operator getOperator() {
    return operator;
  }

  public int getFirstIndex() {
    return firstIndex;
  }

  public int getSecondIndex() {
    return secondIndex;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Operation.class.getSimpleName() + "[", "]")
        .add("operator=" + operator)
        .add("firstIndex=" + firstIndex)
        .add("secondIndex=" + secondIndex)
        .toString();
  }
}