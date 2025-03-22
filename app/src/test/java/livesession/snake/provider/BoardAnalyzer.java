// Provided as part of the course material – author: Prof. Dr. Jörg Winckler
// License status: Unspecified

package livesession.snake.provider;

import livesession.snake.Board;
import livesession.snake.BoardState;

import java.util.Objects;

public class BoardAnalyzer {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(BoardAnalyzer.class);

  public static int countState(Board board, BoardState state){
    Objects.requireNonNull(board);
    int rows = board.size();
    int columns = board.size();
    int counter = 0;

    for (int rowLoop = 0; rowLoop < rows; rowLoop++) {
      for (int columnLoop = 0; columnLoop < columns; columnLoop++) {
        if (state.equals(board.getStateFromPosition(rowLoop, columnLoop))) {
          counter++;
        }
      }
    }
    return counter;
  }
}
