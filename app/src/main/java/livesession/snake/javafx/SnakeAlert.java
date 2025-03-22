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

package livesession.snake.javafx;

import javafx.scene.control.Alert;
import livesession.snake.Reason;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An extension of Alert designed to display exceptions or reasons
 * to the user through a modal dialog.
 */
public class SnakeAlert extends Alert {
  private static final Logger logger =
      LoggerFactory.getLogger(SnakeAlert.class);

  /**
   * Constructs a SnakeAlert for displaying an exception.
   *
   * @param ex The exception to be shown.
   */
  public SnakeAlert(final Exception ex) {
    super(AlertType.ERROR);
    setTitle("What a Terrible Gamer");
    setHeaderText(ex.getClass().getSimpleName());
    setContentText(ex.getMessage());
  }

  /**
   * Constructs a SnakeAlert for displaying a reason.
   *
   * @param reason The reason text to be shown.
   */
  public SnakeAlert(final Reason reason) {
    super(AlertType.ERROR);
    setTitle("What a Terrible Gamer");
    if (reason.getMode() == Reason.Mode.COORDINATE) {
      setContentText(String.format("%s is %s", reason.getCoordinate(), reason.getState()));
    } else {
      setContentText(reason.getMessage());
    }
  }
}