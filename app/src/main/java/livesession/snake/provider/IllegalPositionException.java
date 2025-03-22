package livesession.snake.provider;

import livesession.snake.BoardState;
import livesession.snake.Coordinate;

/**
 * Gets thrown when the snake reaches a position on the board where the snake should not be.
 */
public class IllegalPositionException extends Exception {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(IllegalPositionException.class);

  private Coordinate coordinate;
  private BoardState state;

  /**
   * Instantiates a new IllegalPositionException.
   *
   * @param coordinate The coordinate where the illegal position occurred.
   * @param state      The state of the board at the illegal position.
   */
  public IllegalPositionException(final Coordinate coordinate, final BoardState state) {
    this.coordinate = coordinate;
    this.state = state;
  }

  @Override
  public String getMessage() {
    return "Snake died at " + coordinate + " because of " + state;
  }

  /**
   * Gets the coordinate where the illegal position occurred.
   *
   * @return The coordinate.
   */
  public Coordinate getCoordinate() {
    return coordinate;
  }

  /**
   * Gets the state of the board at the illegal position.
   *
   * @return The board state.
   */
  public BoardState getState() {
    return state;
  }
}