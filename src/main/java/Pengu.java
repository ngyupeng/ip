import java.util.Scanner;
import java.util.ArrayList;

public class Pengu {
    private static final String NAME = "Pengu";
    private final ArrayList<Task> taskList = new ArrayList<>();

    public void run() {
        greet();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            Parser parser = new Parser(input);
            String command = parser.getCommand();

            try {
                switch (command) {
                    case "bye" -> {
                        exit();
                        return;
                    }
                    case "list" -> printTaskList();
                    case "mark" -> processMark(parser);
                    case "unmark" -> processUnmark(parser);
                    case "todo" -> processTodo(parser);
                    case "deadline" -> processDeadline(parser);
                    case "event" -> processEvent(parser);
                    case "delete" -> processDelete(parser);
                    default -> throw new InvalidCommandException();
                }
            } catch (PenguException e) {
                printMessage(e.getMessage());
            }
        }
    }

    private void greet() {
        String greetMessage = String.format("Hello! I'm %s\n" + "What can I do for you?", NAME);
        printMessage(greetMessage);
    }

    private void exit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        printMessage(exitMessage);
    }

    private void printTaskList() {
        StringBuilder taskListString = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            // Every line except the last should end with newline
            if (i > 0) {
                taskListString.append("\n");
            }

            String taskString = (i + 1) + ". " + taskList.get(i);
            taskListString.append(taskString);
        }

        printMessage(taskListString.toString());
    }

    private void processMark(Parser parser) throws PenguException {
        final String markFormat = "mark <index>";

        int taskIndex = parser.getIntField("", markFormat);
        checkTaskIndexBounds(taskIndex);

        taskIndex--;
        taskList.get(taskIndex).markAsDone();

        String message = "Nice! I've marked this task as done:\n  " + taskList.get(taskIndex);
        printMessage(message);
    }

    private void processUnmark(Parser parser) throws PenguException {
        final String unmarkFormat = "unmark <index>";

        int taskIndex = parser.getIntField("", unmarkFormat);
        checkTaskIndexBounds(taskIndex);

        taskIndex--;
        taskList.get(taskIndex).markAsUndone();

        String message = "OK, I've marked this task as not done yet:\n  " + taskList.get(taskIndex);
        printMessage(message);
    }

    private void processDelete(Parser parser) throws PenguException {
        final String deleteFormat = "delete <index>";

        int taskIndex = parser.getIntField("", deleteFormat);
        checkTaskIndexBounds(taskIndex);

        taskIndex--;
        printDeleteTaskMessage(taskList.get(taskIndex));
        taskList.remove(taskIndex);
    }
    
    private void processTodo(Parser parser) throws PenguException {
        final String todoFormat = "todo <description>";

        String taskDesc = parser.getField("", todoFormat);

        Todo todo = new Todo(taskDesc);
        taskList.add(todo);
        printAddTaskMessage(todo);
    }

    private void processDeadline(Parser parser) throws PenguException {
        final String deadlineFormat = "deadline <description> /by <by>";

        String description = parser.getField(" /by ", deadlineFormat);
        String by = parser.getField("", deadlineFormat);

        Deadline deadline = new Deadline(description, by);
        taskList.add(deadline);
        printAddTaskMessage(deadline);
    }

    private void processEvent(Parser parser) throws PenguException {
        final String eventFormat = "event <description> /from <from> /to <to>";

        String description = parser.getField(" /from ", eventFormat);
        String from = parser.getField(" /to ", eventFormat);
        String to = parser.getField("", eventFormat);

        Event event = new Event(description, from, to);
        taskList.add(event);
        printAddTaskMessage(event);
    }

    private void checkTaskIndexBounds(int index) throws PenguException {
        if (index <= 0 || index > taskList.size()) {
            throw new InvalidFieldException(
                    String.format("Expected: integer value in range [1, %d]\n", taskList.size())
                    + "Given: " + index);
        }
    }

    private void printAddTaskMessage(Task task) {
        String message = "Got it, I've added this task:\n  " + task + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        printMessage(message);
    }

    private void printDeleteTaskMessage(Task task) {
        String message = "Noted. I've removed this task:\n  " + task + "\n"
                + "Now you have " + (taskList.size() - 1) + " tasks in the list.";
        printMessage(message);
    }

    private void printMessage(String message) {
        final String PADDING = " ".repeat(4);
        printLine();

        String[] lines = message.split("\n");
        for (String line : lines) {
            System.out.println(PADDING + line);
        }

        printLine();
    }

    private void printLine() {
        final String LINE = "_".repeat(60);
        final String PADDING = " ".repeat(4);
        System.out.println(PADDING + LINE);
    }

    public static void main(String[] args) {
        Pengu pengu = new Pengu();
        pengu.run();
    }
}
