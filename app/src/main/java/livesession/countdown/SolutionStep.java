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