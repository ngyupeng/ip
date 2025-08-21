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
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
