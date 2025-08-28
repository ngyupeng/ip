public class SaveFileException extends PenguException {
    public SaveFileException(String message) {
        super("An error occurred with the save file!\n" + message);
    }
}
