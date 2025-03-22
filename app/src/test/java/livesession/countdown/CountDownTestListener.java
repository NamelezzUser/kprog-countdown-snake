package livesession.countdown;

import java.util.ArrayList;
import java.util.List;

public class CountDownTestListener implements CountdownListener {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(CountDownTestListener.class);

  private boolean isComplete = false;
  private List<Solution> solutionList;
  private SolutionSet solutionSet;
  private NumberSet lastInvalidCalculationNumberSet;
  private Operation lastInvalidCalculationOperation;

  public CountDownTestListener() {
    solutionList = new ArrayList<>();
  }

  @Override
  public void newSolution(Solution solution) {
    logger.debug("newSolution: {}", solution);
    solutionList.add(solution);
    logger.debug("now having {} solutions.", solutionList.size());
  }

  @Override
  public void calculationComplete(SolutionSet solutionSet) {
    logger.debug("calculationComplete: {}", solutionSet);
    this.solutionSet = solutionSet;
    isComplete = true;
  }

  @Override
  public void invalidCalculation(NumberSet numberSet, Operation operation) {
    logger.debug("invalidCalculation: {} - {}", numberSet, operation);
    lastInvalidCalculationNumberSet = numberSet;
    lastInvalidCalculationOperation = operation;
  }

  public boolean isComplete() {
    return isComplete;
  }

  public List<Solution> getSolutionList() {
    return solutionList;
  }

  public SolutionSet getSolutionSet() {
    return solutionSet;
  }

  public NumberSet getLastInvalidCalculationNumberSet() {
    return lastInvalidCalculationNumberSet;
  }

  public Operation getLastInvalidCalculationOperation() {
    return lastInvalidCalculationOperation;
  }
}
