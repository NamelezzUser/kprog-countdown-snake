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

import livesession.countdown.NumberSet;
import livesession.countdown.Operation;

/**
 * The type Calculator.
 */
public class Calculator {
  private Operation operation;
  private NumberSet numberSet;

  /**
   * Instantiates a new Calculator.
   *
   * @param operation the operation
   * @param numberSet the number set
   */
  public Calculator(Operation operation, NumberSet numberSet) {
    this.operation = operation;
    this.numberSet = numberSet;
  }

  /**
   * Calculate int.
   *
   * @return the int
   * @throws NoIntegerCalculationException the no integer calculation exception
   */
  public int calculate() throws NoIntegerCalculationException {

    int firstNum = numberSet.getByIndex(operation.getFirstIndex());
    int secondNum = numberSet.getByIndex(operation.getSecondIndex());

    switch (operation.getOperator()) {
      case ADD:
        return firstNum + secondNum;

      case SUBSTRACT:
        return firstNum - secondNum;

      case MULTIPLY:
        return firstNum * secondNum;

      case DIVIDE:
        if (secondNum == 0) {
          throw new NoIntegerCalculationException("cannot divide by 0");
        }
        if (firstNum % secondNum != 0) {
          throw new NoIntegerCalculationException("division does not result in an Integer");
        }
        return firstNum / secondNum;
      default:
        return 0;
    }
  }
}