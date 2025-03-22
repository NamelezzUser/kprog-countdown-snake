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
import livesession.snake.Coordinate;

/**
 * Realizes the internal view on the snake board. It contains GRASS, FOOD and WALL elements <b>but
 * not the position of the snake itself</b>.
 */
public class InternalBoard extends BaseBoard {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(InternalBoard.class);

  /**
   * Creates an internal board with the given size.
   *
   * @param size size of the board including walls at the border of the board.
   */
  public InternalBoard(final int size) {
    super(size);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (i == 0 || j == 0 || i == size - 1 || j == size - 1) {
          board[i][j] = BoardState.WALL;
        } else {
          board[i][j] = BoardState.GRASS;
        }
      }
    }
  }

  /**
   * Calculates the start position.
   *
   * @return coordinate with the start position
   */
  public Coordinate getStartPosition() {
    return new Coordinate(size / 2, size / 2);
  }

  /**
   * Adds food at the given coordinate.
   *
   * @param coordinate coordinate where the food is placed.
   */
  protected void addFood(Coordinate coordinate) {
    assertPositionIsOnBoard(coordinate);
    board[coordinate.getRow()][coordinate.getColumn()] = BoardState.FOOD;
  }

  /**
   * Removes food at the given coordinate.
   *
   * @param coordinate coordinate where the food has to be removed.
   */
  protected void removeFood(Coordinate coordinate) {
    assertPositionIsOnBoard(coordinate);
    board[coordinate.getRow()][coordinate.getColumn()] = BoardState.GRASS;
  }
}