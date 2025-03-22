// Provided as part of the course material – author: Prof. Dr. Jörg Winckler
// License status: Unspecified

package livesession.countdown;

/**
 * Allows feedback during the calculation.
 */
public interface CountdownListener {
  /**
   * Gets called when a new solution has been found.
   *
   * @param solution new solution
   */
  void newSolution(Solution solution);

  /**
   * Gets called when the calculation has endet.
   *
   * @param solutionSet SolutionSet containing all solutions.
   */
  void calculationComplete(SolutionSet solutionSet);

  /**
   * Gets called when an invalid calculation has been detected.
   *
   * @param numberSet numberSet of the invalid calculation
   * @param operation operation identified as invalid
   */
  void invalidCalculation(NumberSet numberSet, Operation operation);
}