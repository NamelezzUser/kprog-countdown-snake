package livesession.snake.javafx;

import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;
import livesession.snake.javafx.SnakeBoard;
import livesession.snake.javafx.SnakeServiceViewModel;

/**
 * Represents a cell within the Snake board grid.
 */
public class SnakeCell extends Rectangle {
  private BoardState boardState;
  private SnakeServiceViewModel model;
  private int row;
  private int column;

  /**
   * Constructs a new Snake cell with the specified model, row, and column.
   *
   * @param model  The SnakeServiceViewModel associated with the cell.
   * @param row    The row index of the cell.
   * @param column The column index of the cell.
   */
  public SnakeCell(SnakeServiceViewModel model, int row, int column) {
    super(35, 35);

    this.model = model;
    this.row = row;
    this.column = column;
  }

  /**
   * Utilizes the board state and fills the cell according to the state.
   *
   * @param boardState The BoardState representing the state of the cell.
   */
  public void setBoardstate(BoardState boardState) {
    this.boardState = boardState;

    switch (boardState) {
      case GRASS:
        this.setFill(Color.GREEN);
        break;

      case WALL:
        this.setFill(Color.BLACK);
        break;

      case SNAKE:
        this.setFill(Color.MEDIUMPURPLE);
        break;

      case FOOD:
        this.setFill(Color.RED);
        break;

      default:
        this.setFill(Color.YELLOW);
        break;
    }
  }
}