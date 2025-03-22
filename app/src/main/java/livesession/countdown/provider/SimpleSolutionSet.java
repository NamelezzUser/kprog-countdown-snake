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