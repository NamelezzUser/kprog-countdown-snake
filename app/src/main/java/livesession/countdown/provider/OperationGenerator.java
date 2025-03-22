package livesession.countdown.provider;

import java.util.ArrayList;
import java.util.List;
import livesession.countdown.NumberSet;
import livesession.countdown.Operation;
import livesession.countdown.Operator;

/**
 * The type Operation generator.
 */
public class OperationGenerator {

  private NumberSet numberSet;

  /**
   * Constructor.
   *
   * @param numberSet Beinhaltet die maximal 6 Zahlen.
   */
  public OperationGenerator(NumberSet numberSet) {
    this.numberSet = numberSet;
  }

  /**
   * Gives all possible Operations according to size of the NumberSet.
   *
   * @return operations. list
   */
  public List<Operation> generatePermutations() {
    List<Operation> resultList = new ArrayList<>();
    Operator[] operators = Operator.values();
    int size = this.numberSet.size();

    for (Operator operator : operators) {
      for (int firstIndex = 0; firstIndex < size - 1; firstIndex++) {
        for (int secondIndex = firstIndex + 1; secondIndex < size; secondIndex++) {
          resultList.add(new Operation(operator, firstIndex, secondIndex));
          resultList.add(new Operation(operator, secondIndex, firstIndex));
        }
      }
    }
    return resultList;
  }
}