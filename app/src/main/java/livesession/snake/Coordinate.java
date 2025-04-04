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

package livesession.snake;

import java.util.StringJoiner;

/**
 * Realizes a 2D coordinate. This class is intended to be immutable.
 */
public class Coordinate {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Coordinate.class);
  private int row;
  private int column;

  public Coordinate(final int row, final int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  /**
   * Calculates the coordinate of the neighbouring cell in a given direction.
   *
   * @param direction direction where the neighbour lies.
   * @return Coordinate of the neighbour
   */
  public Coordinate getNeighbor(Direction direction) {
    switch (direction) {
      case EAST:
        return new Coordinate(row, column + 1);
      case WEST:
        return new Coordinate(row, column - 1);
      case NORTH:
        return new Coordinate(row - 1, column);
      case SOUTH:
        return new Coordinate(row + 1, column);
      default:
        throw new IllegalArgumentException("Unknown direction: " + direction);
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Coordinate)) {
      return false;
    }

    Coordinate that = (Coordinate) o;

    if (getRow() != that.getRow()) {
      return false;
    }
    return getColumn() == that.getColumn();
  }

  @Override
  public int hashCode() {
    int result = getRow();
    result = 31 * result + getColumn();
    return result;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Coordinate.class.getSimpleName() + "[", "]")
        .add("row=" + row)
        .add("column=" + column)
        .toString();
  }
}