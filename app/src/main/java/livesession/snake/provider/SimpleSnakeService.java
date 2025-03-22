package livesession.snake.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import livesession.snake.Board;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;
import livesession.snake.GameConfiguration;
import livesession.snake.GameState;
import livesession.snake.IllegalConfigurationException;
import livesession.snake.Reason;
import livesession.snake.Snake;
import livesession.snake.SnakeListener;

/**
 * Simple and straight-forward implementation of the ExtendedSnakeService interface.
 */
public class SimpleSnakeService implements ExtendedSnakeService {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleSnakeService.class);
  private GameConfiguration gameConfiguration;
  private InternalBoard board;
  private SimpleSnake snake;
  private GameLoop simpleGameLoop;
  private FoodGenerator foodGenerator;
  private GameState gameState;
  private int score;
  private List<SnakeListener> listeners;

  /**
   * Default constructor. The game uses  default values for the configuration.
   * The default values are defined in the SnakeService interface.
   */
  public SimpleSnakeService() {
    listeners = new ArrayList<>();
    gameConfiguration = new GameConfiguration(DEFAULT_SIZE, DEFAULT_VELOCITY,
        DEFAULT_NUMBER_OF_FOOD);
    initialize();
  }

  /**
   * Initialize.
   */
  public void initialize() {
    board = new InternalBoard(gameConfiguration.getSize());
    snake = new SimpleSnake(this);
    score = 0;
    simpleGameLoop = new SimpleGameLoop(this, gameConfiguration.getVelocityInMilliSeconds());
    foodGenerator = new FoodGenerator(this);
    for (int i = 0; i < gameConfiguration.getNumberOfFood(); i++) {
      addFood(foodGenerator.placeFood());
    }
  }


  @Override
  public void start() {
    logger.debug("start");
    simpleGameLoop.resumeGame(); //startGame() in Interface nicht erlaubt
    gameState = GameState.RUNNING;
    notifyListeners((SnakeListener listeners) -> listeners.newGameState(gameState));
    notifyListeners((SnakeListener listeners) -> listeners.updateBoard(getExternalBoard()));
    notifyListeners((SnakeListener listeners) -> listeners.updateScore(score));
  }

  @Override
  public void pause() {
    logger.debug("pause");
    simpleGameLoop.pauseGame();
    gameState = GameState.PAUSED;
    notifyListeners((SnakeListener listeners) -> listeners.newGameState(gameState));
  }

  @Override
  public void resume() {
    logger.debug("resume");
    simpleGameLoop.resumeGame();
    gameState = GameState.RUNNING;
    notifyListeners((SnakeListener listeners) -> listeners.newGameState(gameState));
  }

  @Override
  public void reset() {
    logger.debug("reset");
    simpleGameLoop.stopGame();
    initialize();
    gameState = GameState.PREPARED;
    notifyListeners((SnakeListener listeners) -> listeners.newGameState(gameState));
    notifyListeners((SnakeListener listeners) -> listeners.updateBoard(getExternalBoard()));
    notifyListeners((SnakeListener listeners) -> listeners.updateScore(score));
  }

  @Override
  public void abort() {
    logger.debug("abort");
    simpleGameLoop.stopGame();
    gameState = GameState.ABORTED;
    notifyListeners((SnakeListener listeners) -> listeners.newGameState(gameState));
    notifyListeners((SnakeListener listeners) -> listeners.updateScore(score));
    notifyListeners((SnakeListener listeners) -> listeners.gameEnded(new Reason("Game aborted")));
  }

  @Override
  public void configure(final GameConfiguration configuration) throws
      IllegalConfigurationException {
    if (configuration.getSize() < board.MINIMAL_BOARD_SIZE) {
      throw new IllegalConfigurationException(
          "Configuration does not match the given values! " + configuration.getSize());
    }
    if (configuration.getNumberOfFood() < 1) {
      throw new IllegalConfigurationException(
          "Game cant be launched with less than 1 Food entity! " + configuration.getNumberOfFood());
    }
    if (configuration.getVelocityInMilliSeconds() < 1) {
      throw new IllegalConfigurationException(
          "Game cant be launched with a velocity smaller than 1! "
              + configuration.getVelocityInMilliSeconds());
    }

    gameConfiguration = configuration;

    this.initialize();
    notifyListeners((SnakeListener listeners) -> listeners.updateBoard(null));
    notifyListeners((SnakeListener listeners) -> listeners.updateBoard(getExternalBoard()));
  }

  @Override
  public void moveLeft() {
    logger.debug("moveLeft");
    snake.goLeft();
    notifyListeners((SnakeListener listeners) -> listeners.updateBoard(getExternalBoard()));
  }

  @Override
  public void moveRight() {
    logger.debug("moveRight");
    snake.goRight();
    notifyListeners((SnakeListener listeners) -> listeners.updateBoard(getExternalBoard()));
  }

  /**
   * Notifies all listeners by executing the consumer accept method. The accept method
   * implementation is in our case a lambda expression.
   *
   * @param consumer consumer to be executed.
   */
  private void notifyListeners(Consumer<SnakeListener> consumer) {
    for (SnakeListener listener : listeners) {
      consumer.accept(listener);
    }
  }

  @Override
  public boolean addListener(final SnakeListener listener) {
    logger.debug("addListener: " + listener);
    if (listener == null) {
      return false;
    }

    if (listeners.contains(listener)) {
      return false;
    }

    listeners.add(listener);
    return true;
  }

  @Override
  public boolean removeListener(final SnakeListener listener) {
    logger.debug("removeListener: " + listener);
    return listeners.remove(listener);
  }

  @Override
  public GameConfiguration getConfiguration() {
    return gameConfiguration;
  }

  @Override
  public Snake getSnake() {
    return snake;
  }

  public Board getBoard() {
    return getExternalBoard();
  }

  @Override
  public InternalBoard getInternalBoard() {
    return board;
  }

  @Override
  public Board getExternalBoard() {
    ExternalBoard externalBoard = new ExternalBoard(board, snake);
    return externalBoard;
  }

  @Override
  public void failed(Reason reason) {
    logger.debug("failed: " + reason);
    simpleGameLoop.stopGame();
    gameState = GameState.ABORTED;
    notifyListeners((SnakeListener listeners) -> listeners.newGameState(gameState));
    notifyListeners((SnakeListener listeners) -> listeners.updateScore(score));
    notifyListeners((SnakeListener listeners) -> listeners.gameEnded(reason));
  }

  @Override
  public void triggeredByGameLoop() {
    try {
      advanceSnake();
      notifyListeners((SnakeListener listeners) -> listeners.updateBoard(getExternalBoard()));
    } catch (IllegalPositionException e) {
      failed(new Reason(e.getCoordinate(), e.getState()));
    }
  }

  @Override
  public void advanceSnake() throws IllegalPositionException {
    try {
      logger.debug("advanceSnake");
      snake.advance(); //Coordinate newPosition = vllt?
      notifyListeners((SnakeListener listeners) -> listeners.updateBoard(getExternalBoard()));
    } catch (IllegalStateException e) {
      new IllegalStateException(e.getMessage(), e);
    }
  }

  @Override
  public void addFood(final Coordinate coordinate) {
    logger.debug("addFood: " + coordinate);
    if (board.getStateFromPosition(coordinate).equals(BoardState.FOOD)) {
      throw new IllegalArgumentException("There is already food at this position: " + coordinate);
    }
    board.addFood(coordinate);
    notifyListeners((SnakeListener listeners) -> listeners.updateBoard(getExternalBoard()));
  }

  @Override
  public void foodEaten(final Coordinate coordinate) {
    logger.debug("foodEaten: " + coordinate);
    if (coordinate == null) { // nach essen wäre snake, davor food auf dem feld - hier nichts
      throw new IllegalArgumentException("Invalid coordinate for eaten food!");
    }
    if (gameState != GameState.RUNNING) {
      throw new IllegalStateException("Game not running. Eating food prohibited!");
    }
    board.removeFood(coordinate);
    updateScore(BoardState.FOOD);
    addFood(foodGenerator.placeFood());
    notifyListeners((SnakeListener listeners) -> listeners.updateBoard(getExternalBoard()));
  }

  @Override
  public void updateScore(final BoardState state) {
    logger.debug("updateScore: " + state);
    if (state == BoardState.FOOD) {
      score += 10;
    } else {
      throw new IllegalArgumentException("Unknown state in updateScore: " + state);
    }
    notifyListeners((SnakeListener listeners) -> listeners.updateScore(score));
  }
}