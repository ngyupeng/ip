/**
 * Parser class to retrieve fields from user input
 */
public class Parser {
    private final String input;
    private int curIndex;
    private final String command;

    public Parser(String input) {
        this.input = input;

        int index = input.indexOf(" ");
        if (index == -1) {
            this.command = input;
            curIndex = input.length();
        } else {
            this.command = input.substring(0, index);
            curIndex = index + 1;
        }
    }

    /**
     * Returns command parsed.
     * @return command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns field parsed up to a delimiter string.
     * Throws exception if delimiter not found.
     *
     * @param delim Delimiter string.
     * @param commandFormat Format of command to be parsed, used as error message.
     * @return The parsed field.
     * @throws MissingFieldException If delimiter not found or field is empty.
     */
    public String getField(String delim, String commandFormat) throws MissingFieldException {
        int delimIndex = delim.isEmpty() ? input.length() : input.indexOf(delim, curIndex);

        if (delimIndex == -1) {
            throw new MissingFieldException(commandFormat);
        }

        String field = input.substring(curIndex, delimIndex);
        if (field.isEmpty()) {
            throw new MissingFieldException(commandFormat);
        }

        curIndex = delimIndex + delim.length();
        return field;
    }

    /**
     * Returns integer field parsed up to a delimiter string.
     * Throws exception if delimiter not found, or if field isn't an integer.
     *
     * @param delim Delimiter string.
     * @param commandFormat Format of command to be parsed, used as error message.
     * @return The parsed field.
     * @throws MissingFieldException If delimiter not found or field is empty.
     * @throws InvalidFieldException If field is not integer.
     */
    public int getIntField(String delim, String commandFormat) throws PenguException {
        String field = getField(delim, commandFormat);

        try {
            return Integer.parseInt(field);
        } catch (NumberFormatException e) {
            throw new InvalidFieldException("Expected: integer value field\n"
                    + "Given: " + field);
        }
    }
}
