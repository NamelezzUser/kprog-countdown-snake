package livesession.snake.javafx;

import java.util.Stack;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import livesession.snake.GameState;
import livesession.snake.provider.SimpleSnakeService;

/**
 * A simple demo program to verify the functionality of the JavaFX environment.
 */
public class SnakeLauncher extends Application {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SnakeLauncher.class);

  public SnakeLauncher() {
  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) throws Exception {
    StackPane root = new StackPane();
    SimpleSnakeService service = new SimpleSnakeService();
    SnakeServiceViewModel snakeServiceViewModel = new SnakeServiceViewModel(service);
    SnakeBoard snakeBoard = new SnakeBoard(snakeServiceViewModel);
    root.getChildren().add(snakeBoard);
    SnakeDisplay snakeDisplay = new SnakeDisplay(snakeServiceViewModel, snakeBoard);
    root.getChildren().add(snakeDisplay);
    // new zeugs erstellen und root.getChildren
    primaryStage.setTitle("Bestest Snake ever");
    primaryStage.setScene(new Scene(root, 693, 949));
    primaryStage.show();
    snakeBoard.setKeyboardBinding();
    snakeBoard.updatedBoard(service.getExternalBoard(), service.getExternalBoard());
  }
}