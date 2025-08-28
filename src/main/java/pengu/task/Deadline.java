package pengu.task;

import pengu.DateTimeParser;
import pengu.exception.SaveFileException;

import java.time.LocalDateTime;

/**
 * Class that specifies a task which is a pengu.task.Deadline
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructor for a pengu.task.Deadline instance
     * @param description Description of task
     * @param by When the deadline is due
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a pengu.task.Deadline object as represented in the line in the save file.
     * @param line The line in the save file.
     * @return A deadline represented by the line.
     * @throws SaveFileException If the line is not a valid pengu.task.Deadline representation.
     */
    public static Deadline fromSaveFileFormat(String line) throws SaveFileException {
        String[] fields = line.split(" \\| ");
        if (fields.length != 4) {
            throw new SaveFileException("Unknown task format found in save file:\n" + line);
        }

        boolean isDone = Task.fromIsDoneStr(fields[1]);
        String description = fields[2];
        LocalDateTime by = Task.fromSaveFileDateTime(fields[3]);

        return new Deadline(description, isDone, by);
    }

    /**
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), DateTimeParser.toOutputFormatString(by));
    }

    @Override
    public String toSaveFileFormat() {
        return String.format("D | %s | %s", super.toSaveFileFormat(),
                DateTimeParser.toInputFormatString(by));
    }
}
