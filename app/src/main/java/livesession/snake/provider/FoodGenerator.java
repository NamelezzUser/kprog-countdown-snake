package livesession.snake.provider;

import java.util.Random;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;

/**
 * Simple FoodGenerator class for the snake game.
 */
public class FoodGenerator {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(FoodGenerator.class);
  private final SimpleSnakeService service;
  private final Random random;

  /**
   * Constructor.
   *
   * @param service SnakeService the FoodGenerator is assigned to
   */
  public FoodGenerator(final SimpleSnakeService service) {
    this.service = service;
    // Use a seed to make the series of "random" coordinates deterministic. Helps with tests.
    this.random = new Random();
  }

  Coordinate placeFood() {
    Coordinate coordinate = null;
    coordinate = getRandomCoordinate();
    if (!service.getBoard().getStateFromPosition(coordinate).equals(BoardState.GRASS)) {
      coordinate = getRandomCoordinate();
    }
    return coordinate;
  }

  private Coordinate getRandomCoordinate() {
    int size = service.getBoard().size();

    int row = random.nextInt(size - 2) + 1;
    int column = random.nextInt(size - 2) + 1;

    return new Coordinate(row, column);
  }
}