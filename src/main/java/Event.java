/**
 * Class that specifies a task which is an Event
 * Start and end of event are stored in the class
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Constructor for a Deadline instance
     * @param description Description of task
     * @param from Start of event
     * @param to End of event
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a Event object as represented in the line in the save file.
     * @param line The line in the save file.
     * @return A event represented by the line.
     * @throws SaveFileException If the line is not a valid Event representation.
     */
    public static Event fromSaveFileFormat(String line) throws SaveFileException {
        String[] fields = line.split(" \\| ");
        if (fields.length != 5) {
            throw new SaveFileException("Unknown task format found in save file:\n" + line);
        }

        boolean isDone = Task.fromIsDoneStr(fields[1]);
        String description = fields[2];
        String from = fields[3];
        String to = fields[4];

        return new Event(description, isDone, from, to);
    }

    /**
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

    @Override
    public String toSaveFileFormat() {
        return String.format("E | %s | %s | %s", super.toSaveFileFormat(), from, to);
    }
}
