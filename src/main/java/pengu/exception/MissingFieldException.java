package pengu.exception;

public class MissingFieldException extends PenguException {
    public MissingFieldException(String message) {
        super("Your command is missing a field!\n"
                + "Please output the command in the following format:\n"
                + message);
    }
}