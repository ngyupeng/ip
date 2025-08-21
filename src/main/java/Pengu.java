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
            if (input.equals("bye")) {
                exit();
                break;
            } else if (input.equals("list")) {
                printTaskList();
            } else if (input.startsWith("mark ")) {
                processMark(input);
            } else if (input.startsWith("unmark ")) {
                processUnmark(input);
            } else if (input.startsWith("todo ")) {
                processTodo(input);
            } else if (input.startsWith("deadline ")) {
                processDeadline(input);
            } else if (input.startsWith("event ")) {
                processEvent(input);
            } else {
                // TODO: Handle invalid command
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

    private void processMark(String input) {
        String taskIndexString = input.split(" ", 2)[1];

        // TODO: Check taskIndexString is an integer

        int taskIndex = Integer.parseInt(taskIndexString) - 1;
        taskList.get(taskIndex).markAsDone();

        String message = "Nice! I've marked this task as done:\n  " + taskList.get(taskIndex);
        printMessage(message);
    }

    private void processUnmark(String input) {
        String taskIndexString = input.split(" ", 2)[1];

        // TODO: Check taskIndexString is an integer

        int taskIndex = Integer.parseInt(taskIndexString) - 1;
        taskList.get(taskIndex).markAsUndone();

        String message = "OK, I've marked this task as not done yet:\n  " + taskList.get(taskIndex);
        printMessage(message);
    }
    
    private void processTodo(String input) {
        String taskDesc = input.split(" ", 2)[1];

        Todo todo = new Todo(taskDesc);
        taskList.add(todo);
        printAddTaskMessage(todo);
    }

    private void processDeadline(String input) {
        String deadlineDesc = input.split(" ", 2)[1];
        String[] deadlineFields = deadlineDesc.split(" /by ", 2);

        String description = deadlineFields[0];
        String by = deadlineFields[1];

        Deadline deadline = new Deadline(description, by);
        taskList.add(deadline);
        printAddTaskMessage(deadline);
    }

    private void processEvent(String input) {
        String eventDesc = input.split(" ", 2)[1];
        String[] eventSplitByFrom = eventDesc.split(" /from ", 2);

        String description = eventSplitByFrom[0];
        String[] eventFromTo = eventSplitByFrom[1].split(" /to ", 2);

        String from = eventFromTo[0];
        String to = eventFromTo[1];

        Event event = new Event(description, from, to);
        taskList.add(event);
        printAddTaskMessage(event);
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
