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

package livesession.snake.provider;

import livesession.snake.BoardState;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestInternalBoard {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestInternalBoard.class);

  private static final int TEST_SIZE_1 = 11;
  private InternalBoard board;

  @Before
  public void setup() {
    board = new InternalBoard(TEST_SIZE_1);
  }

  @Test
  public void testGetSize() {
    assertEquals(TEST_SIZE_1, board.size());
  }

  @Test
  public void accessLegalCoordinates() {
    BoardState state0000 = board.getStateFromPosition(0,0);
    assertEquals(BoardState.WALL, state0000);
    BoardState state0101 = board.getStateFromPosition(1,1);
    assertEquals(BoardState.GRASS, state0101);
    BoardState state0100 = board.getStateFromPosition(1,0);
    assertEquals(BoardState.WALL, state0100);
    BoardState state0505 = board.getStateFromPosition(5,5);
    assertEquals(BoardState.GRASS, state0505);
    BoardState state1010 = board.getStateFromPosition(10,10);
    assertEquals(BoardState.WALL, state1010);
    BoardState state1000 = board.getStateFromPosition(10,0);
    assertEquals(BoardState.WALL, state1000);
  }

  @Test
  public void accessIllegalCoordinates() {
    assertTrue(boardThrowsExceptionOnCoordinate(-1,-1));
    assertTrue(boardThrowsExceptionOnCoordinate(TEST_SIZE_1,0));
    assertTrue(boardThrowsExceptionOnCoordinate(0,TEST_SIZE_1));
    assertTrue(boardThrowsExceptionOnCoordinate(TEST_SIZE_1,TEST_SIZE_1));
  }

  private boolean boardThrowsExceptionOnCoordinate(final int row, final int column) {
    try {
      board.getStateFromPosition(row, column);
    } catch (IllegalArgumentException e) {
      return true;
    }
    return false;
  }
}
