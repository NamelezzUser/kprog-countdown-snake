package livesession.countdown.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import livesession.countdown.CountdownListener;
import livesession.countdown.NumberSet;
import livesession.countdown.Operation;
import livesession.countdown.Solution;
import livesession.countdown.SolutionSet;

/**
 * The type Simple countdown listener.
 */
public class SimpleCountdownListener implements CountdownListener {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleCountdownListener.class);
  private List<Solution> solutionList = new ArrayList<>();

  @Override
  public void newSolution(Solution solution) {
    logger.info("newSolution()");
    solutionList.add(solution);
  }

  @Override
  public void calculationComplete(SolutionSet solutionSet) {
    StringBuilder explanation = new StringBuilder();
    Iterator<Solution> iterator = solutionSet.iterator();
    while (iterator.hasNext()) {
      Solution step = iterator.next();
      explanation.append("Abgeschlossen: ").append(step).append("\n");
    }

    for (Solution solution : solutionList) {
      System.out.println(solution);
    }
  }

  @Override
  public void invalidCalculation(NumberSet numberSet, Operation operation) {
    System.err.println(
        "Invalid calculation detected for NumberSet: " + numberSet + ", Operation: " + operation);
  }

  /**
   * Gets called when one calculation is finished.
   *
   * @param solutionSet SolutionSet containing all solutions.
   */
  public void endOfCalculation(SolutionSet solutionSet) {
    System.out.println("Calculation completed. Number of solutions: " + solutionSet.size());
  }
}