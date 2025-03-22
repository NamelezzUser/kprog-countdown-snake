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
