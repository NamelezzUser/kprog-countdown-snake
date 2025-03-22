package livesession.countdown.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import livesession.countdown.Solution;
import livesession.countdown.SolutionStep;

/**
 * The type Immutable solution.
 */
public class ImmutableSolution implements Solution {
  private final List<SolutionStep> stepList;

  /**
   * Instantiates a new Immutable solution.
   *
   * @param stepList the step list
   */
  public ImmutableSolution(List<SolutionStep> stepList) {
    this.stepList =
        Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(stepList)));
  }

  @Override
  public Iterator<SolutionStep> iterator() {
    return stepList.iterator();
  }

  @Override
  public int size() {
    return stepList.size();
  }
}