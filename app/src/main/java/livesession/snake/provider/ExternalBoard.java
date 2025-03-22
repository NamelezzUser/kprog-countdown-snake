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
import livesession.snake.Snake;

/**
 * Board implementation to be given to UI code. Contains all elements: GRASS, WALL, SNAKE, FOOD.
 * This is a copy of the internal board. So the UI code cannot manipulate the internal board.
 */
public class ExternalBoard extends BaseBoard {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ExternalBoard.class);

  /**
   * Creates the external board based on the contents of the internal board.
   *
   * @param internalBoard internal board the contents is copied from
   * @param snake         actual snake
   */
  public ExternalBoard(InternalBoard internalBoard, final SimpleSnake snake) {
    super(internalBoard.size());
    copyContents(internalBoard);
    addSnake(snake);
  }

  /**
   * Copies the slow changing content from the internal board.
   *
   * @param internalBoard internal board with WALL, GRASS, FOOD, no snake.
   */
  private void copyContents(final InternalBoard internalBoard) {
    for (int i = 0; i < internalBoard.board.length; i++) {
      for (int j = 0; j < internalBoard.board.length; j++) {
        this.board[i][j] = internalBoard.board[i][j];
      }
    }
  }

  /**
   * Adds the snake to the board.
   *
   * @param snake snake to be added.
   */
  private void addSnake(Snake snake) {
    for (Coordinate coordinate : snake.getPosition()) {
      this.board[coordinate.getRow()][coordinate.getColumn()] = BoardState.SNAKE;
    }
  }
}