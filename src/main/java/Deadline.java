/**
 * Class that specifies a task which is a Deadline
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Constructor for a Deadline instance
     * @param description Description of task
     * @param by When the deadline is due
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a Deadline object as represented in the line in the save file.
     * @param line The line in the save file.
     * @return A deadline represented by the line.
     * @throws SaveFileException If the line is not a valid Deadline representation.
     */
    public static Deadline fromSaveFileFormat(String line) throws SaveFileException {
        String[] fields = line.split(" \\| ");
        if (fields.length != 4) {
            throw new SaveFileException("Unknown task format found in save file:\n" + line);
        }

        boolean isDone = Task.fromIsDoneStr(fields[1]);
        String description = fields[2];
        String by = fields[3];

        return new Deadline(description, isDone, by);
    }

    /**
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }

    @Override
    public String toSaveFileFormat() {
        return String.format("D | %s | %s", super.toSaveFileFormat(), by);
    }
}
