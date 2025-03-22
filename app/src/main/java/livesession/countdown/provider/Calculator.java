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