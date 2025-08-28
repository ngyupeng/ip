package pengu.task;

import pengu.exception.SaveFileException;

/**
 * Class that specifies a task which is a todo.
 */
public class Todo extends Task {
    /**
     * Constructor for a Todo instance
     * @param description Description of task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a Todo object as represented in the line in the save file.
     * @param line The line in the save file.
     * @return A todo represented by the line.
     * @throws SaveFileException If the line is not valid Todo representation.
     */
    public static Todo fromSaveFileFormat(String line) throws SaveFileException {
        String[] fields = line.split(" \\| ");
        if (fields.length != 3) {
            throw new SaveFileException("Unknown task format found in save file:\n" + line);
        }

        boolean isDone = Task.fromIsDoneStr(fields[1]);
        String description = fields[2];

        return new Todo(description, isDone);
    }

    /**
     * @return String representation of the Todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toSaveFileFormat() {
        return String.format("T | %s", super.toSaveFileFormat());
    }
}
