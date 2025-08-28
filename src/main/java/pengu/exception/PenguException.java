package pengu.exception;

/**
 * Base exception class for the project.
 */
public class PenguException extends Exception {
    public PenguException(String message) {
        super("Oh no! " + message);
    }
}
