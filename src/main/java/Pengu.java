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
            } else {
                addTask(input);
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

    private void addTask(String taskDesc) {
        taskList.add(new Task(taskDesc));

        String addTaskMessage = "added: " + taskDesc;
        printMessage(addTaskMessage);
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
        int spaceIndex = input.indexOf(" ");
        String taskIndexString = input.substring(spaceIndex + 1);

        // TODO: Check taskIndexString is an integer

        int taskIndex = Integer.parseInt(taskIndexString) - 1;
        taskList.get(taskIndex).markAsDone();

        String message = "Nice! I've marked this task as done:\n  " + taskList.get(taskIndex);
        printMessage(message);
    }

    private void processUnmark(String input) {
        int spaceIndex = input.indexOf(" ");
        String taskIndexString = input.substring(spaceIndex + 1);

        // TODO: Check taskIndexString is an integer

        int taskIndex = Integer.parseInt(taskIndexString) - 1;
        taskList.get(taskIndex).markAsUndone();

        String message = "OK, I've marked this task as not done yet:\n  " + taskList.get(taskIndex);
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
