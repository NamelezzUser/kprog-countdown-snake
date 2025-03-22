package livesession.countdown.provider;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import livesession.countdown.Solution;
import livesession.countdown.SolutionStep;

/**
 * The type Simple solution.
 */
public class DequeSolution implements Solution {

  private Deque<SolutionStep> stepDeque;

  public DequeSolution() {
    stepDeque = new ArrayDeque<>();
  }

  @Override
  public int size() {
    return stepDeque.size();
  }

  @Override
  public Iterator<SolutionStep> iterator() {
    return stepDeque.iterator();
  }

  /**
   * Adds a solution step to the deque.
   *
   * @param step solution step to be added
   */
  public void push(SolutionStep step) {
    stepDeque.push(step);
  }

  /**
   * Removes and returns the last added solution step from the deque.
   *
   * @return last added solution step
   */
  public SolutionStep pop() {
    return stepDeque.pop();
  }

  /**
   * Gets the immutable solution.
   *
   * @return the immutable solution
   */
  public ImmutableSolution getImmutableSolution() {
    return new ImmutableSolution(new ArrayList<>(stepDeque));
  }
}