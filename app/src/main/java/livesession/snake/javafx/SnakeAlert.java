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