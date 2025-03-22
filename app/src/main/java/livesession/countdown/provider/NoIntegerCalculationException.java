package livesession.countdown.provider;

/**
 * The type No integer calculation exception.
 */
public class NoIntegerCalculationException extends Exception {
  /**
   * Instantiates a new No integer calculation exception.
   *
   * @param errorMessage the error message
   */
  public NoIntegerCalculationException(String errorMessage) {
    super(errorMessage);
  }

  /**
   * Instantiates a new No integer calculation exception.
   */
  public NoIntegerCalculationException() {
    super();
  }
}