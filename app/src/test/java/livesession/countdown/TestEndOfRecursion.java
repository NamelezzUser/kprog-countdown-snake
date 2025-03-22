package livesession.countdown;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import livesession.countdown.provider.DepthFirstCountdownSolver;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestEndOfRecursion {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestEndOfRecursion.class);
  private CountdownSolver solver;
  private static final int HUNDRED = 100;
  private CountDownTestListener countDownTestListener;

  @Before
  public void setup() {
    solver = new DepthFirstCountdownSolver();
    countDownTestListener = new CountDownTestListener();
    solver.addListener(countDownTestListener);
  }

  @Test
  public void checkTargetIsInNumberSet() {
    NumberSet setWithTargetNumber = solver.createNumberSet(2, 5, 4, 6, 25, HUNDRED);
    solver.solveCalculation(setWithTargetNumber, HUNDRED);
    // TODO: if solving includes threads this will perhaps fail.
    assertTrue(countDownTestListener.isComplete());
    assertEquals(1, countDownTestListener.getSolutionList().size());
  }

  @Test
  public void CheckOnlyOneIntInNumberSet() {
    NumberSet setWithOneNumber = solver.createNumberSet(345);
    solver.solveCalculation(setWithOneNumber, HUNDRED);
    // TODO: if solving includes threads this will perhaps fail.
    assertTrue(countDownTestListener.isComplete());
    assertEquals(0, countDownTestListener.getSolutionList().size());
  }
  @Test
  public void CheckOnlyTargetNumberInNumberSet() {
    NumberSet setWithOneNumber = solver.createNumberSet(HUNDRED);
    solver.solveCalculation(setWithOneNumber, HUNDRED);
    // TODO: if solving includes threads this will perhaps fail.
    assertTrue(countDownTestListener.isComplete());
    assertEquals(1, countDownTestListener.getSolutionList().size());
  }
}
