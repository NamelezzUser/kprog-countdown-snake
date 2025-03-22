package livesession.snake.provider;

import java.util.LinkedList;
import java.util.List;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;
import livesession.snake.Direction;
import livesession.snake.Snake;

/**
 * Simple and straight-forward implementation of the Snake interface.
 */
public class SimpleSnake implements Snake {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleSnake.class);
  private final ExtendedSnakeService service;
  // The LinkedList allows direct access to the head (first) and tail (last) of the snake.
  private final LinkedList<Coordinate> position;
  private Direction direction;

  /**
   * Creates a snake. A snake has at the beginning a length of 1.
   *
   * @param service service as single source of truth to get the internal board and to notify on
   *                events during wiggling.
   */
  public SimpleSnake(final ExtendedSnakeService service) {
    this.service = service;
    position = new LinkedList<>();
    position.addFirst(service.getInternalBoard().getStartPosition());
    direction = Direction.EAST;
  }

  /**
   * Advances the snake in the direction the snake is oriented to.
   *
   * @return new position of the head of the snake
   * @throws IllegalPositionException if the position is not allowed
   */
  public Coordinate advance() throws IllegalPositionException {
    //current position of the head of the snake
    Coordinate currentHeadPosition = position.getFirst();
    //new position of the head of the snake to the direction the snake is going to move
    Coordinate newHeadPosition = currentHeadPosition.getNeighbor(direction);
    BoardState newState = assertNewPositionIsPossible(newHeadPosition);

    if (position.contains(newHeadPosition)) {
      throw new IllegalPositionException(newHeadPosition, BoardState.SNAKE);
    }

    if (newState == BoardState.FOOD) {
      logger.info("food");
      position.addFirst(newHeadPosition); //add new position of the head of the snake
      service.foodEaten(newHeadPosition);
    } else {
      position.addFirst(newHeadPosition);
      position.removeLast(); //if no food is eaten the tail should move with the snake
    }
    return newHeadPosition;
  }

  private BoardState assertNewPositionIsPossible(final Coordinate newHead)
      throws IllegalPositionException {
    //State of Board at the new position
    BoardState newState = service.getInternalBoard().getStateFromPosition(newHead);
    if (newState == BoardState.WALL) {
      throw new IllegalPositionException(newHead, newState);
    }
    return newState;
  }

  @Override
  public List<Coordinate> getPosition() {
    return position;
  }

  @Override
  public Direction getDirection() {
    return direction;
  }

  /**
   * Changes the direction of the snake by turning left.
   */
  public void goLeft() {
    direction = direction.getLeft();
  }

  /**
   * Changes the direction of the snake by turning right.
   */
  public void goRight() {
    direction = direction.getRight();
  }
}