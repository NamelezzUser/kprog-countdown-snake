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
 * Signals that the GameConfiguration contains values not appropriate for the game.
 */
public class IllegalConfigurationException extends Exception {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(IllegalConfigurationException.class);

  public IllegalConfigurationException(final String message) {
    super(message);
  }
}