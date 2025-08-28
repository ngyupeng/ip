package pengu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pengu.exception.InvalidFieldException;
import pengu.exception.PenguException;

/**
 * Class to help with parsing date time strings and re-outputting in various formats.
 */
public class DateTimeParser {
    public static final String inputDateTimeFormat = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter inputDateTimeFormatter
            = DateTimeFormatter.ofPattern(inputDateTimeFormat);
    public static final String outputDateTimeFormat = "MMM dd yyyy HH:mm";
    public static final DateTimeFormatter outputDateTimeFormatter
            = DateTimeFormatter.ofPattern(outputDateTimeFormat);

    /**
     * Convert string to LocalDateTime object.
     * @param str String in "yyyy-mm-dd HH:mm" format;
     * @return LocalDateTime object corresponding to string.
     * @throws PenguException If string isn't in correct format.
     */
    public static LocalDateTime fromDateTimeString(String str) throws PenguException {
        try {
            return LocalDateTime.parse(str, inputDateTimeFormatter);
        } catch (DateTimeParseException e) {
            String errorMessage = String.format(
                    "Expected: date time string in format %s (e.g. 2025-07-23 14:30)\n", inputDateTimeFormat)
                    + "Got: " + str;
            throw new InvalidFieldException(errorMessage);
        }
    }

    /**
     * Convert LocalDateTime object to string in "MMM dd yyyy HH:mm" format
     * (e.g. Oct 15 2025 12:30)
     * @param dateTime LocalDateTime object
     * @return String in target format.
     */
    public static String toInputFormatString(LocalDateTime dateTime) {
        return dateTime.format(inputDateTimeFormatter);
    }

    /**
     * Convert LocalDateTime object to string in "yyyy-MM-dd HH:mm" format
     * (e.g. 2025-07-23 14:30)
     * @param dateTime LocalDateTime object
     * @return String in target format.
     */
    public static String toOutputFormatString(LocalDateTime dateTime) {
        return dateTime.format(outputDateTimeFormatter);
    }
}