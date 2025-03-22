package livesession.countdown.provider;

import livesession.countdown.CountdownListener;
import livesession.countdown.NumberSet;
import livesession.countdown.Operation;
import livesession.countdown.Solution;
import livesession.countdown.SolutionSet;

/**
 * The type Count down reporting listener.
 */
public class CountDownReportingListener implements CountdownListener {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(CountDownReportingListener.class);

  /**
   * The Solution explainer.
   */
  SolutionExplainer solutionExplainer = new SolutionExplainer();

  @Override
  public void newSolution(Solution solution) {
    String explainer = solutionExplainer.explainSolutionByText(solution);
    logger.info("Found solution: {}", explainer);
  }

  @Override
  public void calculationComplete(SolutionSet solutionSet) {
    String explainer = solutionExplainer.explainSolutionSetByText(solutionSet);
    logger.info("Found all solution: {}", explainer);
  }

  @Override
  public void invalidCalculation(NumberSet numberSet, Operation operation) {

  }
}
