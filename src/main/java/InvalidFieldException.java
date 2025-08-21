public class InvalidFieldException extends PenguException {
    public InvalidFieldException(String message) {
        super("Your field(s) provided caused the following error:\n"
                + message);
    }
}