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

package livesession.snake.provider;

import livesession.snake.Board;
import livesession.snake.BoardState;
import livesession.snake.GameConfiguration;
import livesession.snake.IllegalConfigurationException;
import livesession.snake.SnakeService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class TestSnakeServiceGoodCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestSnakeServiceGoodCases.class);
  private SimpleSnakeService simpleSnakeService;
  private SnakeService snakeService;
  private ExtendedSnakeService extendedSnakeService;

  @Before
  public void setup() {
    simpleSnakeService = new SimpleSnakeService();
    extendedSnakeService = simpleSnakeService;
    snakeService = simpleSnakeService;
  }

  @Test
  public void testDefaultConfigurationByBoard() {
    Board board = snakeService.getBoard();
    int boardSize = (int) board.size();
    assertNotNull("A default configuration should create a board.", board);
    assertEquals("The board size should be " + SnakeService.DEFAULT_SIZE,
        SnakeService.DEFAULT_SIZE, boardSize);
    assertEquals("There should be " + SnakeService.DEFAULT_NUMBER_OF_FOOD + " Food elements on " +
            "the board", SnakeService.DEFAULT_NUMBER_OF_FOOD,
        BoardAnalyzer.countState(board, BoardState.FOOD));
  }

  @Test
  public void testDefaultConfigurationByGameConfiguration() {
    GameConfiguration configuration = snakeService.getConfiguration();
    int configSize = configuration.getSize();
    int configNoOfFood = configuration.getNumberOfFood();
    int configVelocity = configuration.getVelocityInMilliSeconds();
    assertNotNull("A default configuration should create a board.", configuration);
    assertEquals("The board size should be " + SnakeService.DEFAULT_SIZE,
        SnakeService.DEFAULT_SIZE, configSize);
    assertEquals("There should be " + SnakeService.DEFAULT_NUMBER_OF_FOOD + " Food elements on " +
        "the board", SnakeService.DEFAULT_NUMBER_OF_FOOD, configNoOfFood);
    assertEquals("The default velocity should be " + SnakeService.DEFAULT_VELOCITY,
        SnakeService.DEFAULT_VELOCITY,
        configVelocity);
  }

  @Test
  public void testNewConfiguration() throws IllegalConfigurationException {
    GameConfiguration config = new GameConfiguration(11, 300, 2);
    snakeService.configure(config);
    Board board = snakeService.getBoard();
    int boardSize = board.size();
    assertNotNull("A default configuration should create a board.", board);
    assertEquals("The board size should be " + 11,
        11, boardSize);
    assertEquals("There should be " + 2 + " Food elements on the board",
        2, BoardAnalyzer.countState(board, BoardState.FOOD));
  }
}