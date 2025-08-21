public class Pengu {
    private static final String NAME = "Pengu";

    private static void greet() {
        String greetMessage = String.format("Hello! I'm %s\n" + "What can I do for you?", NAME);
        printMessage(greetMessage);
    }

    private static void exit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        printMessage(exitMessage);
    }

    private static void printMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    private static void printLine() {
        final String LINE = "_".repeat(60);
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        greet();
        exit();
    }
}
