package livesession.countdown;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import livesession.countdown.provider.DepthFirstCountdownSolver;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;

public class TestSimpleGoodCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestSimpleGoodCases.class);
  private DepthFirstCountdownSolver solver;
  private CountDownTestListener countDownTestListener;

  @Before
  public void setup() {
    solver = new DepthFirstCountdownSolver();
    countDownTestListener = new CountDownTestListener();
    solver.addListener(countDownTestListener);
  }

  @Test
  public void testTwoNumbersInNumberSetNotWorking() {
    NumberSet setWithNumbers = solver.createNumberSet(2,5);
    solver.solveCalculation(setWithNumbers, 11);
    assertTrue(countDownTestListener.isComplete());
    assertEquals(0, countDownTestListener.getSolutionList().size());
  }

  @Test
  public void testTwoNumbersInNumberSetWithSolution() {
    NumberSet setWithNumbers = solver.createNumberSet(2,5);
    solver.solveCalculation(setWithNumbers, 10);
    assertTrue(countDownTestListener.isComplete());
    assertTrue(1 <= countDownTestListener.getSolutionList().size());
    SolutionSet set = countDownTestListener.getSolutionSet();
    assertTrue(1 <= set.size());
    Iterator<Solution> iterator = set.iterator();
    while (iterator.hasNext()) {
      Solution solution = iterator.next();
      logger.debug("Solution has {} elements.", solution.size());
      assertTrue(setWithNumbers.size() > solution.size());
    }
  }

  @Test
  public void testExampleFromSlides() {
    int[] arrayWithNumbers = {25, 50, 6, 5, 7, 2};
    NumberSet setWithNumbers = solver.createNumberSet(25, 50, 6, 5, 7, 2);
    solver.solveCalculation(setWithNumbers, 717);
    assertTrue(countDownTestListener.isComplete());
    System.out.println("No Solutions: " + countDownTestListener.getSolutionList().size());
    System.out.println("No Solutions: " + countDownTestListener.getSolutionSet().size());
  }

}
