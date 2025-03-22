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

import livesession.snake.Board;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;

/**
 * Simple and straight-forward implementation of the Board interface.
 */
public class BaseBoard implements Board {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(BaseBoard.class);
  /**
   * The Size.
   */
  protected int size;
  /**
   * The Board.
   */
  protected BoardState[][] board;

  /**
   * Creates a board with the given size.
   *
   * @param size size of the board to be created
   * @throws IllegalArgumentException if the size is smaller than the minimal board size.
   */
  public BaseBoard(final int size) {
    assertSizeIsGreaterThan(size, MINIMAL_BOARD_SIZE);
    this.size = size;
    board = new BoardState[size][size];
  }

  /**
   * Checks if the requested size of the board is valid.
   *
   * @param size             requested size
   * @param minimalBoardSize official minimal size
   */
  protected void assertSizeIsGreaterThan(final int size, final int minimalBoardSize) {
    if (size < minimalBoardSize) {
      throw new IllegalArgumentException("Board size of " + size + " is smaller than the minimal "
          + "board size of " + minimalBoardSize);
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public BoardState getStateFromPosition(final Coordinate coordinate) {
    return getStateFromPosition(coordinate.getRow(), coordinate.getColumn());
  }

  @Override
  public BoardState getStateFromPosition(final int row, final int column) {
    assertPositionIsOnBoard(row, column);
    return board[row][column];
  }

  /**
   * Asserts that the specified coordinate is within the boundaries of the game board.
   *
   * @param coordinate The coordinate to be checked.
   */
  protected void assertPositionIsOnBoard(Coordinate coordinate) {
    assertPositionIsOnBoard(coordinate.getRow(), coordinate.getColumn());
  }

  /**
   * Asserts that the specified row and column values are within the boundaries of the game board.
   *
   * @param row    The row value to be checked.
   * @param column The column value to be checked.
   * @throws IllegalArgumentException if the row or column is outside the valid board boundaries.
   */
  protected void assertPositionIsOnBoard(int row, int column) {
    if (row < 0 || column < 0) {
      throw new IllegalArgumentException("Invalid position: (" + row + ", " + column + ")");
    }
    if (row >= this.size || column >= this.size) {
      throw new IllegalArgumentException("Invalid position: (" + row + ", " + column + ")");
    }
  }
}