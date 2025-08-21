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
            try {
                if (input.equals("bye")) {
                    exit();
                    break;
                } else if (input.equals("list")) {
                    printTaskList();
                } else if (input.startsWith("mark")) {
                    processMark(input);
                } else if (input.startsWith("unmark")) {
                    processUnmark(input);
                } else if (input.startsWith("todo")) {
                    processTodo(input);
                } else if (input.startsWith("deadline")) {
                    processDeadline(input);
                } else if (input.startsWith("event")) {
                    processEvent(input);
                } else {
                    throw new InvalidCommandException();
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

    private void processMark(String input) throws PenguException {
        String taskIndexString = input.split(" ", 2)[1];

        int taskIndex = parseTaskIndex(taskIndexString) - 1;
        taskList.get(taskIndex).markAsDone();

        String message = "Nice! I've marked this task as done:\n  " + taskList.get(taskIndex);
        printMessage(message);
    }

    private void processUnmark(String input) throws PenguException {
        String taskIndexString = input.split(" ", 2)[1];

        int taskIndex = parseTaskIndex(taskIndexString) - 1;
        taskList.get(taskIndex).markAsUndone();

        String message = "OK, I've marked this task as not done yet:\n  " + taskList.get(taskIndex);
        printMessage(message);
    }
    
    private void processTodo(String input) throws PenguException {
        final String todoFormat = "todo <description>";

        String[] inputSplit = input.split(" ", 2);
        if (inputSplit.length < 2 || inputSplit[1].isEmpty()) {
            throw new MissingFieldException(todoFormat);
        }

        String taskDesc = inputSplit[1];

        Todo todo = new Todo(taskDesc);
        taskList.add(todo);
        printAddTaskMessage(todo);
    }

    private void processDeadline(String input) throws PenguException {
        final String deadlineFormat = "deadline <description> /by <by>";

        String[] inputSplit = input.split(" ", 2);
        if (inputSplit.length < 2 || inputSplit[1].isEmpty()) {
            throw new MissingFieldException(deadlineFormat);
        }

        String deadlineDesc = inputSplit[1];
        String[] deadlineFields = deadlineDesc.split(" /by ", 2);

        if (deadlineFields.length < 2 || deadlineFields[1].isEmpty()) {
            throw new MissingFieldException(deadlineFormat);
        }

        String description = deadlineFields[0];
        String by = deadlineFields[1];

        Deadline deadline = new Deadline(description, by);
        taskList.add(deadline);
        printAddTaskMessage(deadline);
    }

    private void processEvent(String input) throws PenguException {
        final String eventFormat = "event <description> /from <from> /to <to>";

        String[] inputSplit = input.split(" ", 2);
        if (inputSplit.length < 2 || inputSplit[1].isEmpty()) {
            throw new MissingFieldException(eventFormat);
        }

        String eventDesc = inputSplit[1];
        String[] eventSplitByFrom = eventDesc.split(" /from ", 2);

        if (eventSplitByFrom.length < 2 || eventSplitByFrom[1].isEmpty()) {
            throw new MissingFieldException(eventFormat);
        }

        String description = eventSplitByFrom[0];
        String[] eventFromTo = eventSplitByFrom[1].split(" /to ", 2);

        if (eventFromTo.length < 2 || eventFromTo[1].isEmpty()) {
            throw new MissingFieldException(eventFormat);
        }

        String from = eventFromTo[0];
        String to = eventFromTo[1];

        Event event = new Event(description, from, to);
        taskList.add(event);
        printAddTaskMessage(event);
    }

    private int parseTaskIndex(String taskIndexString) throws PenguException {
        try {
            int taskIndex = Integer.parseInt(taskIndexString);
            if (taskIndex <= 0 || taskIndex > taskList.size()) {
                throw new InvalidFieldException(
                        String.format("Expected: integer value in range [1, %d]\n", taskList.size())
                        + "Given: " + taskIndex);
            }

            return taskIndex;
        } catch (NumberFormatException e) {
            throw new InvalidFieldException("Expected: integer value for task index\n"
                    + "Given: " + taskIndexString);
        }
    }

    private void printAddTaskMessage(Task task) {
        String message = "Got it, I've added this task:\n  " + task + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
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
