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
