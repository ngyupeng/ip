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
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
