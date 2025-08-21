public class InvalidCommandException extends PenguException {
    public InvalidCommandException() {
        super("Invalid command received :(");
    }
}