/**
 * This file is part of countdown-snake-java.
 *
 * Copyright (c) 2025 Enes Korkmaz, Mira Wagner and Mae Weiland
 *
 * countdown-snake-java is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License Version 3 as published by
 * the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package livesession.snake.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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
    primaryStage.setTitle("Bestest Snake ever");
    primaryStage.setScene(new Scene(root, 693, 949));
    primaryStage.show();
    snakeBoard.setKeyboardBinding();
    snakeBoard.updatedBoard(service.getExternalBoard(), service.getExternalBoard());
  }
}