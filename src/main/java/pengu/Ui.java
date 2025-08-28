package pengu;

import pengu.task.Task;
import pengu.task.TaskList;

/**
 * Class to handle printing responses to user input.
 */
public class Ui {
    private static final String NAME = "Pengu";

    /**
     * Prints greeting message.
     */
    public void greet() {
        String greetMessage = String.format("Hello! I'm %s\n" + "What can I do for you?", NAME);
        printMessage(greetMessage);
    }

    /**
     * Prints farewell message.
     */
    public void exit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        printMessage(exitMessage);
    }

    /**
     * Prints out the task list.
     *
     * @param taskList pengu.task.TaskList to print.
     */
    public void printTaskList(TaskList taskList) {
        printMessage(taskList.toString());
    }

    /**
     * Prints a message that says a task has been added, and how many tasks are in the list.
     *
     * @param task     pengu.task.Task added.
     * @param taskList pengu.task.TaskList the task is added to.
     */
    public void printAddTaskMessage(Task task, TaskList taskList) {
        String message = "Got it, I've added this task:\n  " + task + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.";
        printMessage(message);
    }

    /**
     * Prints a message that says a task has been deleted, and how many tasks are left in the list.
     *
     * @param task     pengu.task.Task deleted.
     * @param taskList pengu.task.TaskList the task is deleted from.
     */
    public void printDeleteTaskMessage(Task task, TaskList taskList) {
        String message = "Noted. I've removed this task:\n  " + task + "\n"
                + "Now you have " + (taskList.getSize() - 1) + " tasks in the list.";
        printMessage(message);
    }

    /**
     * Prints a message that says a task has been marked as done.
     *
     * @param task pengu.task.Task marked as done.
     */
    public void printMarkTaskMessage(Task task) {
        String message = "Nice! I've marked this task as done:\n  " + task;
        printMessage(message);
    }

    /**
     * Prints a message that says a task has been marked as undone.
     *
     * @param task pengu.task.Task marked as undone.
     */
    public void printUnmarkTaskMessage(Task task) {
        String message = "OK, I've marked this task as not done yet:\n  " + task;
        printMessage(message);
    }

    /**
     * Prints out an error message, emphasising with "ERROR!!".
     *
     * @param message Error message.
     */
    public void printError(String message) {
        printMessage("ERROR!!\n" + message);
    }

    private void printMessage(String message) {
        final String padding = " ".repeat(4);
        printLine();

        String[] lines = message.split("\n");
        for (String line : lines) {
            System.out.println(padding + line);
        }

        printLine();
    }

    private void printLine() {
        final String line = "_".repeat(60);
        final String padding = " ".repeat(4);
        System.out.println(padding + line);
    }
}
