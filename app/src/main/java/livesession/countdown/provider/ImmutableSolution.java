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