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

import static livesession.snake.SnakeService.DEFAULT_NUMBER_OF_FOOD;
import static livesession.snake.SnakeService.DEFAULT_SIZE;
import static livesession.snake.SnakeService.DEFAULT_VELOCITY;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import livesession.snake.GameConfiguration;
import livesession.snake.GameState;


/**
 * The type Snake display.
 */
public class SnakeDisplay extends VBox {
  private Button resumeButton;
  private Button startGameButton;
  private Button pauseButton;
  private Button resetButton;
  private Button endGameButton;
  private Button prepareGameButton;
  private SnakeServiceViewModel viewModel;
  private SnakeBoard board;
  private HBox menuBar = new HBox(10);
  private VBox buttonBox = new VBox();
  private Label scoreLabel = new Label("Score: 5000 Points");
  private VBox allSliders = new VBox();
  private HBox valoLabelSlider = new HBox(5);
  private Label veloLabel = new Label("Snake speed:");
  private Slider velocity = new Slider(250.0, 500.0, 10.0);
  private VBox velocityBox = new VBox(5);
  private HBox veloButtons = new HBox(1);
  private Button upVeloB;
  private Button downVeloB;
  private Label veloValue = new Label();
  private HBox foodLabelSlider = new HBox(5);
  private Label foodLabel = new Label("Food Number:");
  private Slider foodSlider = new Slider(1.0, 5.0, 1.0);
  private VBox foodBox = new VBox(5);
  private HBox foodButtons = new HBox(1);
  private Button upFoodB;
  private Button downFoodB;
  private Label foodValue = new Label();
  private HBox sizeLabelSlider = new HBox(20);
  private Label sizeLabel = new Label("Board size");
  private Slider sizeSlider = new Slider(4.0, 16.0, 4.0);
  private VBox sizeBox = new VBox(5);
  private HBox sizeButtons = new HBox();
  private Button upSizeB;
  private Button downSizeB;
  private Label sizeValue = new Label();
  private GameConfiguration gameConfig =
      new GameConfiguration(DEFAULT_SIZE, DEFAULT_VELOCITY, DEFAULT_NUMBER_OF_FOOD);
  private SimpleDoubleProperty sizeProperty = new SimpleDoubleProperty(gameConfig.getSize());
  private SimpleDoubleProperty veloProperty =
      new SimpleDoubleProperty(gameConfig.getVelocityInMilliSeconds());
  private SimpleDoubleProperty foodProperty =
      new SimpleDoubleProperty(gameConfig.getNumberOfFood());

  /**
   * Instantiates a new Snake display.
   *
   * @param viewModel the view model
   * @param board     the board
   */
  public SnakeDisplay(SnakeServiceViewModel viewModel, SnakeBoard board) {
    this.viewModel = viewModel;
    this.board = board;

    setAllSliders();
    setButtons();
    setLabels();
    layoutBox();

    getChildren().addAll(menuBar, board);
    this.setSpacing(25.0);
  }

  private void setLabels() {
    scoreLabel.textProperty().bind(viewModel.scoreProperty().asString("Score: %d Points"));
  }

  /**
   * Sets buttons.
   */
  public void setButtons() {
    resumeButton = new Button("Resume");
    startGameButton = new Button("Start Game");
    pauseButton = new Button("Pause");
    resetButton = new Button("Reset");
    endGameButton = new Button("End Game");
    prepareGameButton = new Button("Prepare Game");

    startGameButton.setOnAction(event -> {
      viewModel.start();
    });
    pauseButton.setOnAction(event -> {
      viewModel.pause();
    });
    resumeButton.setOnAction(event -> {
      viewModel.resume();
    });
    resetButton.setOnAction(event -> {
      viewModel.reset();
    });
    endGameButton.setOnAction(event -> {
      viewModel.abort();
    });

    viewModel.gameStateProperty().addListener(this::gameStateChanged);

    disableMenuElements(viewModel.gameStateProperty().getValue());
  }

  private void gameStateChanged(Observable observable) {
    setGameState(viewModel.gameStateProperty().getValue());
  }

  /**
   * Disable menu elements.
   *
   * @param gameState the game state
   */
  public void disableMenuElements(GameState gameState) {
    startGameButton.setDisable(gameState != GameState.PREPARED);
    resumeButton.setDisable(gameState != GameState.PAUSED);
    pauseButton.setDisable(gameState != GameState.RUNNING);
    resetButton.setDisable(gameState != GameState.PAUSED && gameState != GameState.ABORTED);
    endGameButton.setDisable(gameState != GameState.PAUSED);
    allSliders.setDisable(gameState != GameState.PREPARED);
  }

  /**
   * Sets game state.
   *
   * @param gameState the game state
   */
  public void setGameState(GameState gameState) {
    disableMenuElements(gameState);
  }

  /**
   * Sets all sliders and buttons to configure the board size, snake velocity and food number .
   */
  public void setAllSliders() {
    upSizeB = new Button(">");
    downSizeB = new Button("<");
    upSizeB.setId("upSize");
    downSizeB.setId("downSize");

    upVeloB = new Button(">");
    downVeloB = new Button("<");
    upVeloB.setId("upVelo");
    downVeloB.setId("downVelo");

    upFoodB = new Button(">");
    downFoodB = new Button("<");
    upFoodB.setId("upFood");
    downFoodB.setId("downFood");

    //Board Size Slider
    sizeSlider.setShowTickMarks(true);
    sizeSlider.setShowTickLabels(true);
    sizeSlider.setMajorTickUnit(4.0);
    sizeSlider.setBlockIncrement(4.0);
    sizeSlider.snapToTicksProperty().set(true);
    sizeSlider.valueProperty().bindBidirectional(sizeProperty);
    Bindings.bindBidirectional(sizeValue.textProperty(), sizeProperty,
        new NumberStringConverter());
    //Velocity Slider
    velocity.setShowTickMarks(true);
    velocity.setShowTickLabels(true);
    velocity.setMajorTickUnit(50.0);
    velocity.setBlockIncrement(50.0);
    velocity.snapToTicksProperty().set(true);
    velocity.valueProperty().bindBidirectional(veloProperty);
    Bindings.bindBidirectional(veloValue.textProperty(), veloProperty,
        new NumberStringConverter());
    //Food Number Slider
    foodSlider.setShowTickMarks(true);
    foodSlider.setShowTickLabels(true);
    foodSlider.setMajorTickUnit(1.0);
    foodSlider.setBlockIncrement(1.0);
    foodSlider.snapToTicksProperty().set(true);
    foodSlider.valueProperty().bindBidirectional(foodProperty);
    Bindings.bindBidirectional(foodValue.textProperty(), foodProperty,
        new NumberStringConverter());

    upSizeB.setOnAction(event -> {
      sizeProperty.setValue(sizeProperty.getValue() + 4.0);
      updateSize(sizeProperty);
      viewModel.configure(gameConfig);
    });

    downSizeB.setOnAction(event -> {
      sizeProperty.setValue(sizeProperty.getValue() - 4.0);
      updateSize(sizeProperty);
      viewModel.configure(gameConfig);
    });

    upVeloB.setOnAction(event -> {
      veloProperty.setValue(veloProperty.getValue() + 10.0);
      updateVelocity(veloProperty);
      viewModel.configure(gameConfig);
    });
    downVeloB.setOnAction(event -> {
      veloProperty.setValue(veloProperty.getValue() - 10.0);
      updateVelocity(veloProperty);
      viewModel.configure(gameConfig);
    });

    upFoodB.setOnAction(event -> {
      foodProperty.setValue(foodProperty.getValue() + 1.0);
      updateFoodNumber(foodProperty);
      viewModel.configure(gameConfig);
    });

    downFoodB.setOnAction(event -> {
      foodProperty.setValue(foodProperty.getValue() - 1.0);
      updateFoodNumber(foodProperty);
      viewModel.configure(gameConfig);
    });
  }

  /**
   * Updates the size of the board based on the provided SimpleDoubleProperty.
   *
   * @param sizeProperty The SimpleDoubleProperty containing the new size value.
   */
  private void updateSize(SimpleDoubleProperty sizeProperty) {
    double newSize = sizeProperty.getValue();
    gameConfig.setSize(newSize);
  }

  /**
   * Updates the velocity of the sanke based on the provided SimpleDoubleProperty.
   *
   * @param veloProperty The SimpleDoubleProperty containing the new velocity value.
   */
  private void updateVelocity(SimpleDoubleProperty veloProperty) {
    double newVelocity = veloProperty.getValue();
    gameConfig.setVelocity(newVelocity);
  }

  /**
   * Updates the number of food items on the board based on the provided SimpleDoubleProperty.
   *
   * @param foodProperty The SimpleDoubleProperty containing the new food number value.
   */
  private void updateFoodNumber(SimpleDoubleProperty foodProperty) {
    double newFoodNumber = foodProperty.getValue();
    gameConfig.setNumberOfFood(newFoodNumber);
  }

  /**
   * Arranges various elements in a set of boxes for layout purposes.
   */
  public void layoutBox() {
    //add to HBox then VBox
    veloButtons.getChildren().addAll(downVeloB, veloValue, upVeloB);
    veloButtons.setSpacing(73);
    valoLabelSlider.getChildren().addAll(veloLabel, velocity);
    velocityBox.getChildren().addAll(valoLabelSlider, veloButtons);
    //add to HBox then VBox
    foodButtons.getChildren().addAll(downFoodB, foodValue, upFoodB);
    foodButtons.setSpacing(78);
    foodLabelSlider.getChildren().addAll(foodLabel, foodSlider);
    foodBox.getChildren().addAll(foodLabelSlider, foodButtons);
    //add to HBox then VBox
    sizeButtons.getChildren().addAll(downSizeB, sizeValue, upSizeB);
    sizeButtons.setSpacing(75);
    sizeLabelSlider.getChildren().addAll(sizeLabel, sizeSlider);
    sizeBox.getChildren().addAll(sizeLabelSlider, sizeButtons);
    //combine Sliders + buttons to VBox
    allSliders.getChildren().addAll(velocityBox, foodBox, sizeBox); //if, clear use *allSliders
    allSliders.setSpacing(10);
    //menu buttons to VBox
    buttonBox.getChildren().addAll(startGameButton, resumeButton, pauseButton, resetButton,
        endGameButton);
    buttonBox.setSpacing(20);
    //combine Menu Buttons + Score Board + Sliders - to HBox
    menuBar.getChildren().addAll(buttonBox, allSliders, scoreLabel);
    menuBar.setSpacing(150);
  }
}