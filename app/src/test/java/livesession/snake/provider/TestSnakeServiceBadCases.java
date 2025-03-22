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

import livesession.snake.GameConfiguration;
import livesession.snake.IllegalConfigurationException;
import livesession.snake.SnakeService;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class TestSnakeServiceBadCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestSnakeServiceBadCases.class);

  private SimpleSnakeService simpleSnakeService;
  private SnakeService snakeService;
  private ExtendedSnakeService extendedSnakeService;
  private Method configurationMethod;

  @Before
  public void setup(){
    simpleSnakeService = new SimpleSnakeService();
    extendedSnakeService = simpleSnakeService;
    snakeService = simpleSnakeService;
  }

  @Test
  public void checkCreationOfWrongConfigurationNegativeSize() throws Throwable {
    GameConfiguration configuration = new GameConfiguration(-11, 200, 3);
    try {
      logger.info("Trying with negative board size.");
      snakeService.configure(configuration);
      fail("Configuration was invalid. Should not be accepted.");
    } catch (IllegalConfigurationException e) {
      String message = e.getMessage();
      assertFalse("The message of the exception should not be empty.", message.isEmpty());
    }
  }

  @Test
  public void checkCreationOfWrongConfigurationNegativeVelocity() throws Throwable{
    GameConfiguration configuration = new GameConfiguration(11, -200, 3);
    try {
      logger.info("Trying with negative velocity.");
      snakeService.configure(configuration);
      fail("Configuration was invalid. Should not be accepted.");
    } catch (IllegalConfigurationException e) {
      String message = e.getMessage();
      assertFalse("The message of the exception should not be empty.", message.isEmpty());
    }
  }

  @Test
  public void checkCreationOfWrongConfigurationNegativeNumberOfFood() throws Throwable{
    GameConfiguration configuration = new GameConfiguration(11, 200, -3);
    try {
      logger.info("Trying with negative number of food.");
      snakeService.configure(configuration);
      fail("Configuration was invalid. Should not be accepted.");
    } catch (IllegalConfigurationException e) {
      String message = e.getMessage();
      assertFalse("The message of the exception should not be empty.", message.isEmpty());
    }
  }
}