public class PenguException extends Exception {
    public PenguException(String message) {
        super("Oh no! " + message);
    }
}
