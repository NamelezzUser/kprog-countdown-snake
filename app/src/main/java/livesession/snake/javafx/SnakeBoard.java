package livesession.snake.javafx;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import livesession.snake.Board;
import livesession.snake.Coordinate;

/**
 * Represents the game board in the Snake game.
 */
public class SnakeBoard extends GridPane {
  private final Map<Coordinate, SnakeCell> coordinateSnakeCellMap = new HashMap<>();
  private SnakeServiceViewModel model;

  /**
   * Constructs a new Snake board with the specified model.
   *
   * @param model The SnakeServiceViewModel associated with the board.
   */
  public SnakeBoard(SnakeServiceViewModel model) {
    this.model = model;
    this.model.boardProperty.addListener(this::boardChanged);
  }

  private void boardChanged(ObservableValue<? extends Board> observableValue, Board oldBoard,
                            Board newBoard) {
    if (!Platform.isFxApplicationThread()) {

      Platform.runLater(() -> this.updatedBoard(oldBoard, newBoard));
    } else {
      updatedBoard(oldBoard, newBoard);
    }
  }

  /**
   * Handles the change in direction based on the pressed keys.
   *
   * @param keyEvent The KeyEvent representing the key press.
   */
  public void changeDirection(KeyEvent keyEvent) {
    if (keyEvent.getCode() == KeyCode.A) { //oder 37 - Arrow Links
      model.moveLeft();
    } else if (keyEvent.getCode() == KeyCode.D) { //oder 39 - Arrow Rechts
      model.moveRight();
    }
  }

  /**
   * Sets up keyboard binding for direction changes.
   */
  public void setKeyboardBinding() {
    this.getScene().setOnKeyPressed(this::changeDirection);
  }

  /**
   * Removes keyboard binding for direction changes.
   */
  public void removeKeyboardBinding() {
    this.getScene().setOnKeyPressed(null);
  }

  /**
   * Updates the game board based on the old and new board states.
   *
   * @param oldBoard The previous state of the board.
   * @param newBoard The new state of the board.
   */
  public void updatedBoard(Board oldBoard, Board newBoard) {
    if (newBoard == null && oldBoard != null) {
      this.getChildren().clear();
      this.coordinateSnakeCellMap.clear();
    }
    if (newBoard != null) {
      for (int column = 0; column < newBoard.size(); column++) {
        for (int row = 0; row < newBoard.size(); row++) {
          SnakeCell snakeCell = new SnakeCell(this.model, row, column);
          coordinateSnakeCellMap.put(new Coordinate(row, column), snakeCell);
          this.add(snakeCell, column, row);
          snakeCell.setBoardstate(newBoard.getStateFromPosition(new Coordinate(row, column)));
        }
      }
    }
  }
}