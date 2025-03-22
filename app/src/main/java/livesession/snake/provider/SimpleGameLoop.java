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

/**
 * A simple implementation of the GameLoop interface for the Snake game.
 */
public class SimpleGameLoop extends Thread implements GameLoop {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleGameLoop.class);
  private final SimpleSnakeService service;
  private final int sleepTime;
  private boolean isPaused = true;
  private boolean shouldExit = false;

  /**
   * Constructs a SimpleGameLoop.
   *
   * @param service   The ExtendedSnakeService to be notified every loop.
   * @param sleepTime The time between two notifications in milliseconds.
   */
  public SimpleGameLoop(final SimpleSnakeService service,
                        final int sleepTime) {
    this.service = service;
    this.sleepTime = sleepTime;
    this.setDaemon(true);
    this.start();
  }

  @Override
  public void run() {
    try {
      while (!shouldExit) {
        if (isPaused) {
          synchronized (this) {
            wait();
          }
        } else {
          service.triggeredByGameLoop();
          Thread.sleep(sleepTime);
        }
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);

    }
  }

  @Override
  public void pauseGame() {
    isPaused = true;
    synchronized (this) {
      notifyAll();
    }
  }

  @Override
  public void resumeGame() {
    isPaused = false;
    synchronized (this) {
      notifyAll();
    }
  }

  @Override
  public void stopGame() {
    isPaused = false;
    shouldExit = true;
    synchronized (this) {
      notifyAll();
    }
    logger.debug("Game Stopped");
  }
}