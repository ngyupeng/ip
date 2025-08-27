public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task instance
     *
     * @param description Description of task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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
     * @return 1 if done, 0 if undone.
     */
    public String isDoneString() {
        return isDone ? "1" : "0";
    }

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
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns the string representation to store in the save file.
     * @return String in save file format.
     */
    public String toSaveFileFormat() {
        return String.format("%s | %s", isDoneString(), description);
    }
}
