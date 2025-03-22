package livesession.countdown;

import java.util.Iterator;

/**
 * Contains a set of Solution objects for a Countdown math riddle. The set may be empty.
 */
public interface SolutionSet {

  int size();

  Iterator<Solution> iterator();
}