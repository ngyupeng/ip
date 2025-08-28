package pengu.task;

import java.time.LocalDateTime;

import pengu.DateTimeParser;
import pengu.exception.PenguException;
import pengu.exception.SaveFileException;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a pengu.task.Task instance
     *
     * @param description Description of task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks that the isDone field is represented with "0" or "1" in the save file.
     * Returns the corresponding boolean value if valid.
     *
     * @param str String representation of isDone field.
     * @return Boolean for isDone.
     * @throws SaveFileException If the isDone field isn't represented as "0" or "1".
     */
    public static boolean fromIsDoneStr(String str) throws SaveFileException {
        switch (str) {
        case "1" -> {
            return true;
        }
        case "0" -> {
            return false;
        }
        default -> throw new SaveFileException("Expected: 0 or 1 for is done representation in save file\n"
                + "Got: " + str);
        }
    }

    /**
     * Converts from string in format "yyyy-MM-dd HH:mm" to LocalDateTime object.
     *
     * @param str The date time string.
     * @return Converted LocalDateTime object.
     * @throws SaveFileException If string is not in required format.
     */
    public static LocalDateTime fromSaveFileDateTime(String str) throws SaveFileException {
        try {
            return DateTimeParser.fromDateTimeString(str);
        } catch (PenguException e) {
            String errorMessage = String.format(
                    "Expected: date time string in format %s in save file\n", DateTimeParser.inputDateTimeFormat)
                    + "Got: " + str;
            throw new SaveFileException(errorMessage);
        }
    }

    /**
     * @return status icon that shows whether the task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns string representation of whether the task is done.
     *
     * @return 1 if done, 0 if undone.
     */
    public String isDoneString() {
        return isDone ? "1" : "0";
    }

    /**
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns the string representation to store in the save file.
     *
     * @return String in save file format.
     */
    public String toSaveFileFormat() {
        return String.format("%s | %s", isDoneString(), description);
    }
}
