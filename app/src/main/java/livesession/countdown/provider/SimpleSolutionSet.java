package livesession.countdown.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import livesession.countdown.Solution;
import livesession.countdown.SolutionSet;

/**
 * The type Simple solution set.
 *
 * <p>Beinhaltet auch Zwischenlösungen (Interim Solution), welche an SimpleCountdownListener
 * übergeben werden können.
 */
public class SimpleSolutionSet implements SolutionSet { //extends Solution?
  private List<Solution> solutionList;

  public SimpleSolutionSet() {
    solutionList = new ArrayList<>();
  }

  @Override
  public int size() {
    return solutionList.size();
  }

  @Override
  public Iterator<Solution> iterator() {
    return solutionList.iterator();
  }

  public void addNewSolution(Solution solution) {
    solutionList.add(solution);
  }
}