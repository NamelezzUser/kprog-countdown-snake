package livesession.countdown.provider;

import java.util.Iterator;
import livesession.countdown.Solution;
import livesession.countdown.SolutionSet;
import livesession.countdown.SolutionStep;

/**
 * The type Solution explainer.
 */
public class SolutionExplainer {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SolutionExplainer.class);

  /**
   * Explain solution set by text string.
   *
   * @param solutionSet the solution set
   * @return the string
   */
  public String explainSolutionSetByText(SolutionSet solutionSet) {
    StringBuilder explanation = new StringBuilder();

    if (solutionSet != null) {
      Iterator<Solution> solutionIterator = solutionSet.iterator();
      int solutionCount = 0;

      while (solutionIterator.hasNext()) {
        Solution solution = solutionIterator.next();
        explanation.append("Solution ").append(++solutionCount).append(":").append("\n");

        if (solution != null) {
          explanation.append(explainSolutionByText(solution));
        } else {
          explanation.append("No solution available.");
        }

        explanation.append("\n\n");
      }
    } else {
      explanation.append("No SolutionSet available.");
    }

    return explanation.toString();
  }

  /**
   * Explain solution by text string.
   *
   * @param solution the solution
   * @return the string
   */
  public String explainSolutionByText(Solution solution) {
    StringBuilder explanation = new StringBuilder();

    if (solution != null) {
      int stepCount = solution.size();

      Iterator<SolutionStep> stepIterator = solution.iterator();
      while (stepIterator.hasNext()) {
        SolutionStep step = stepIterator.next();
        explanation.append("Step ").append(stepCount--).append(": ").append(step.toString());
        if (stepIterator.hasNext()) {
          explanation.append(" -> ");
        }
      }
    } else {
      explanation.append("No solution available.");
    }

    return explanation.toString();
  }
}