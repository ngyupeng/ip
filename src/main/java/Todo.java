/**
 * Class that specifies a task which is a Todo
 */
public class Todo extends Task {
    /**
     * Constructor for a Todo instance
     * @param description Description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * @return String representation of the Todo task
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
