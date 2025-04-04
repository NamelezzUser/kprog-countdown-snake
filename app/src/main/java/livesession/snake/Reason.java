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
 * Contains the reason for the termination of the game.
 */
public class Reason {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Reason.class);
  private Coordinate coordinate;
  private BoardState state;
  private String message;
  private final Mode mode;

  /**
   * Creates a reason based on the BoardState at a certain position.
   *
   * @param coordinate coordinate where the fatal thing has happened.
   * @param state      reason why it is fatal.
   */
  public Reason(final Coordinate coordinate, final BoardState state) {
    this.coordinate = coordinate;
    this.state = state;
    mode = Mode.COORDINATE;
  }

  /**
   * Creates a reason based on a message. This is for cases where the reason is not related to a
   * coordinate, e.g. aborting the game by the player.
   *
   * @param message reason
   */
  public Reason(final String message) {
    this.message = message;
    mode = Mode.MESSAGE;
  }

  /**
   * Returns the coordinate where the fatal thing had happened.
   *
   * @return coordinate where it happened
   */
  public Coordinate getCoordinate() {
    if (mode.equals(Mode.MESSAGE)) {
      throw new IllegalStateException("Reason is in message mode.");
    }
    return coordinate;
  }

  /**
   * Reason for the fatal thing.
   *
   * @return reason
   */
  public BoardState getState() {
    if (mode.equals(Mode.MESSAGE)) {
      throw new IllegalStateException("Reason is in message mode.");
    }
    return state;
  }

  /**
   * Returns the message with the reason.
   *
   * @return reason
   */
  public String getMessage() {
    if (mode.equals(Mode.COORDINATE)) {
      throw new IllegalStateException("Reason is in coordinate mode.");
    }
    return message;
  }

  public Mode getMode() {
    return mode;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Reason.class.getSimpleName() + "[", "]")
        .add("coordinate=" + coordinate)
        .add("state=" + state)
        .add("message='" + message + "'")
        .add("mode=" + mode)
        .toString();
  }

  /**
   * Modes the reason can be in.
   */
  public enum Mode {
    MESSAGE, COORDINATE
  }
}
