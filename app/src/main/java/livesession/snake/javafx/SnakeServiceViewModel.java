package livesession.snake.javafx;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import livesession.snake.Board;
import livesession.snake.GameConfiguration;
import livesession.snake.GameState;
import livesession.snake.IllegalConfigurationException;
import livesession.snake.Reason;
import livesession.snake.SnakeListener;
import livesession.snake.provider.SimpleSnakeService;


/**
 * ViewModel for the SnakeService interface. This class acts as an intermediary between the Snake
 * game service (e.g., SimpleSnakeService) and the JavaFX user interface. It provides properties and
 * methods to update the UI based on the game state and handle user interactions.
 */
public class SnakeServiceViewModel implements SnakeListener {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SnakeServiceViewModel.class);
  private SimpleSnakeService simpleSnake;
  private IntegerProperty scoreProperty;
  public ObjectProperty<Board> boardProperty;
  private ObjectProperty<GameState> gameStateProperty;
  private ObjectProperty<GameConfiguration> configurationProperty;
  private List<SnakeListener> listeners;

  /**
   * Constructs a new SnakeServiceViewModel instance.
   *
   * @param service The SnakeService to be used.
   */
  public SnakeServiceViewModel(SimpleSnakeService service) {
    this.simpleSnake = service;
    scoreProperty = new SimpleIntegerProperty(0);
    boardProperty = new SimpleObjectProperty<>(null);
    gameStateProperty = new SimpleObjectProperty<>(GameState.PREPARED);
    listeners = new ArrayList<>();
    service.addListener(this);
  }

  /**
   * Gets the score property.
   *
   * @return The IntegerProperty representing the score.
   */
  public IntegerProperty scoreProperty() {
    return scoreProperty;
  }

  /**
   * Gets the board property.
   *
   * @return The ObjectProperty representing the game board.
   */
  public ObjectProperty<Board> boardProperty() {
    return boardProperty;
  }

  /**
   * Gets the game state property.
   *
   * @return The ObjectProperty representing the game state.
   */
  public ObjectProperty<GameState> gameStateProperty() {
    return gameStateProperty;
  }

  /**
   * Gets the configuration property.
   *
   * @return The ObjectProperty representing the game configuration.
   */
  public ObjectProperty<GameConfiguration> configurationProperty() {
    return configurationProperty;
  }

  @Override
  public void updateScore(int score) {
    Platform.runLater(() -> {
      logger.debug("Update board");
      scoreProperty.set(score);
    });
  }

  @Override
  public void updateBoard(Board board) {
    Platform.runLater(() -> {
      logger.debug("Update board");
      boardProperty.set(board);
    });
  }

  @Override
  public void newGameState(GameState state) {
    Platform.runLater(() -> {
      logger.debug("New Game State");
      gameStateProperty.set(state);
    });
  }

  @Override
  public void gameEnded(Reason reason) {
    Platform.runLater(() -> new SnakeAlert(reason).show());
    logger.debug("Game Ended");
  }

  /**
   * Resets the game.
   */
  public void reset() {
    try {
      logger.debug("Resetting");
      simpleSnake.reset();
    } catch (IllegalStateException e) {
      logger.info("resetfail");
      Platform.runLater(() -> new SnakeAlert(e).show());
    }
  }

  /**
   * Starts the game.
   */
  public void start() {
    try {
      simpleSnake.start();
    } catch (IllegalStateException e) {
      logger.info("startfail");

      Platform.runLater(() -> new SnakeAlert(e).show());
    }
  }

  /**
   * Aborts the game.
   */
  public void abort() {
    try {
      logger.debug("Abort");
      simpleSnake.abort();
    } catch (IllegalStateException e) {
      logger.info("abortfail");
      Platform.runLater(() -> new SnakeAlert(e).show());
    }
  }

  /**
   * Pauses the game.
   */
  public void pause() {
    try {
      logger.debug("Paused");
      simpleSnake.pause();
    } catch (IllegalStateException e) {
      logger.info("pausefail");
      Platform.runLater(() -> new SnakeAlert(e).show());
    }
  }

  /**
   * Resumes the game.
   */
  public void resume() {
    try {
      logger.debug("Resuming");
      simpleSnake.resume();
    } catch (IllegalStateException e) {
      logger.info("resumefail");
      Platform.runLater(() -> new SnakeAlert(e).show());
    }
  }

  /**
   * Changes the direction of the snake to left.
   */
  public void moveLeft() {
    try {
      simpleSnake.moveLeft();
    } catch (IllegalStateException e) {
      Platform.runLater(() -> new SnakeAlert(e).show());
    }
  }

  /**
   * Changes the direction of the snake to right.
   */
  public void moveRight() {
    try {
      simpleSnake.moveRight();
    } catch (IllegalStateException e) {
      Platform.runLater(() -> new SnakeAlert(e).show());
    }
  }

  /**
   * Configures the game with the specified configuration.
   *
   * @param configuration The GameConfiguration to be applied.
   */
  public void configure(GameConfiguration configuration) {
    try {
      logger.info("Configuring");
      simpleSnake.configure(configuration);
    } catch (IllegalConfigurationException e) {
      logger.info("configurationffeil");
      Platform.runLater(() -> new SnakeAlert(e).show());
    }
  }
}