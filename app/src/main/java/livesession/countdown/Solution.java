// Provided as part of the course material – author: Prof. Dr. Jörg Winckler
// License status: Unspecified

package livesession.countdown;

import java.util.Iterator;

/**
 * Solution for a countdown math riddle.
 * The solution may contain zero or more SolutionStep objects.
 */
public interface Solution {
  Iterator<SolutionStep> iterator();

  int size();
}