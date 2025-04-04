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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import livesession.countdown.CountdownListener;
import livesession.countdown.CountdownSolver;
import livesession.countdown.NumberSet;
import livesession.countdown.Operation;
import livesession.countdown.Solution;
import livesession.countdown.SolutionStep;

/**
 * The type Depth first countdown solver.
 */
public class DepthFirstCountdownSolver implements CountdownSolver {
  private DequeSolution solutionTracker = new DequeSolution();
  private SimpleSolutionSet solutionList = new SimpleSolutionSet();
  private ArrayList<CountdownListener> listeners = new ArrayList<>();
  private int target = -1;

  public DepthFirstCountdownSolver() {
    addListener(new CountDownReportingListener());
  }

  @Override
  public void addListener(CountdownListener listener) {
    listeners.add(listener);
  }

  @Override
  public void removeListener(CountdownListener listener) {
    listeners.remove(listener);
  }

  @Override
  public void solveCalculation(NumberSet numberSet, int target) {
    this.target = target;
    solutionTracker = new DequeSolution();
    solveRecursively(numberSet);
    for (CountdownListener listener : listeners) {
      listener.calculationComplete(solutionList);
    }
  }

  private void solveRecursively(NumberSet numberSet) {

    if (numberSet.contains(target)) {
      solutionList.addNewSolution(solutionTracker.getImmutableSolution());
      for (CountdownListener listener : listeners) {
        listener.newSolution(solutionTracker);
      }
      return;
    }

    if (numberSet.size() == 1) {
      return;
    }

    OperationGenerator operations = new OperationGenerator(numberSet);

    for (Operation operation : operations.generatePermutations()) {
      Calculator calculator = new Calculator(operation, numberSet);

      try {
        int newResult = calculator.calculate();
        ArrayList<Integer> numbers = numberSet.getNumbersAsArrayList();
        numbers.set(operation.getFirstIndex(), newResult);
        numbers.remove(operation.getSecondIndex());

        NumberSet newNumberSet = new SimpleNumberSet(numbers);
        solutionTracker.push(new SolutionStep(newNumberSet, operation));
        solveRecursively(newNumberSet);
        solutionTracker.pop();

      } catch (NoIntegerCalculationException e) {
        //Ignored to allow the tests to run properly
        //for (CountdownListener listener : listeners) {
        //listener.invalidCalculation(numberSet, operation);
      }
    }
  }

  @Override
  public NumberSet createNumberSet(Integer... numbers) {
    ArrayList<Integer> numberList = new ArrayList<>(List.of(numbers));
    return new SimpleNumberSet(numberList);
  }

  @Override
  public String explainSolutionByText(Solution solution) {
    StringBuilder explanation = new StringBuilder();
    Iterator<SolutionStep> iterator = solution.iterator();
    while (iterator.hasNext()) {
      SolutionStep step = iterator.next();
      explanation.append("Schritt: ").append(step).append("\n");
    }

    return explanation.toString();
  }
}