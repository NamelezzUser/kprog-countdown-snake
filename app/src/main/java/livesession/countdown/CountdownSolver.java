// Provided as part of the course material – author: Prof. Dr. Jörg Winckler
// License status: Unspecified

package livesession.countdown;

/**
 * Solves math riddles known from the TV show "8 out of 10 Cats do Countdown".
 */
public interface CountdownSolver {
  /**
   * Adds a CountdownListener.
   *
   * @param listener listener to be added.
   */
  void addListener(CountdownListener listener);

  /**
   * Removes a listener.
   *
   * @param listener listener to be removed.
   */
  void removeListener(CountdownListener listener);

  /**
   * Solves a riddle by calculating all possible solutions.
   *
   * <p>This call is synchronous, meaning, the call ends when all
   * calculations have been done. Maybe, e.g. when triggered by a GUI,
   * it is helpful to run this call in a separate thread.
   *
   * @param numberSet NumberSet to be used for the calculations
   * @param target    target value which has to be reached by the calculations
   */
  void solveCalculation(NumberSet numberSet, int target);

  /**
   * Creates a NumberSet with the given numbers.
   *
   * @param numbers numbers to be included in the NumberSet
   * @return NumberSet containing the given numbers
   */
  NumberSet createNumberSet(Integer... numbers);

  /**
   * Explains the solution in the order of the solution steps needed to obtain the solution.
   *
   * @param solution the solution
   * @return the string
   */
  String explainSolutionByText(Solution solution);
}