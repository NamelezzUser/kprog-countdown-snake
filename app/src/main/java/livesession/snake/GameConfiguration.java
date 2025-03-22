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

import java.util.StringJoiner;

/**
 * Configures a snake game. This class is intended to be immutable.
 */
public class GameConfiguration {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GameConfiguration.class);
  private int size;
  private int velocityInMilliSeconds;
  private int numberOfFood;

  /**
   * Creates a new game configuration.
   *
   * @param size                   size of the board. The minimum size of a board can be found in
   *                               the class Board.
   * @param velocityInMilliSeconds initial time in milliseconds between to wiggles of the snake
   * @param numberOfFood           number of food items on the board when the game starts
   */
  public GameConfiguration(final int size, final int velocityInMilliSeconds,
                           final int numberOfFood) {
    this.size = size;
    this.velocityInMilliSeconds = velocityInMilliSeconds;
    this.numberOfFood = numberOfFood;
  }

  public int getSize() {
    return size;
  }

  public int getVelocityInMilliSeconds() {
    return velocityInMilliSeconds;
  }

  public int getNumberOfFood() {
    return numberOfFood;
  }

  /**
   * Set the Board Size.
   *
   * @param size the new Size.
   */
  public void setSize(double size) {
    this.size = (int) size;
  }

  /**
   * Set the Velocity.
   *
   * @param velocityInMilliSeconds the new velocity in milliseconds
   */
  public void setVelocity(double velocityInMilliSeconds) {
    this.velocityInMilliSeconds = (int) velocityInMilliSeconds;
  }

  /**
   * Set the food number.
   *
   * @param numberOfFood the new number of food.
   */
  public void setNumberOfFood(double numberOfFood) {
    this.numberOfFood = (int) numberOfFood;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", GameConfiguration.class.getSimpleName() + "[", "]")
        .add("size=" + size)
        .add("velocityInMilliSeconds=" + velocityInMilliSeconds)
        .add("numberOfFood=" + numberOfFood)
        .toString();
  }
}