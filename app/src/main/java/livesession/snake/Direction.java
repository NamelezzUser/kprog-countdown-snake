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

package livesession.snake;

/**
 * Directions a snake can wiggle.
 */
public enum Direction {
  NORTH, EAST, SOUTH, WEST;

  private Direction left;
  private Direction right;

  static {
    NORTH.left = WEST;
    NORTH.right = EAST;
    SOUTH.left = EAST;
    SOUTH.right = WEST;
    EAST.left = NORTH;
    EAST.right = SOUTH;
    WEST.left = SOUTH;
    WEST.right = NORTH;
  }

  /**
   * Returns the direction when the snake is turning left.
   *
   * @return new direction
   */
  public Direction getLeft() {
    return left;
  }

  /**
   * Returns the direction when the snake is turning right.
   *
   * @return new direction
   */
  public Direction getRight() {
    return right;
  }
}