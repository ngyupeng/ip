package pengu;

import java.time.LocalDateTime;
import java.util.Scanner;

import pengu.exception.InvalidCommandException;
import pengu.exception.PenguException;
import pengu.task.Deadline;
import pengu.task.Event;
import pengu.task.TaskList;
import pengu.task.Todo;

public class Pengu {
    private TaskList taskList;
    private Ui ui;

    public static void main(String[] args) {
        Pengu pengu = new Pengu();
        pengu.run();
    }

    public void run() {
        ui = new Ui();
        ui.greet();

        try {
            Save save = new Save();
            taskList = save.load();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                Parser parser = new Parser(input);
                String command = parser.getCommand();

                try {
                    switch (command) {
                    case "bye" -> {
                        ui.exit();
                        save.save(taskList);
                        return;
                    }
                    case "list" -> ui.printTaskList(taskList);
                    case "mark" -> processMark(parser);
                    case "unmark" -> processUnmark(parser);
                    case "todo" -> processTodo(parser);
                    case "deadline" -> processDeadline(parser);
                    case "event" -> processEvent(parser);
                    case "delete" -> processDelete(parser);
                    default -> throw new InvalidCommandException();
                    }
                } catch (PenguException e) {
                    ui.printError(e.getMessage());
                }
            }
        } catch (PenguException e) {
            ui.printError(e.getMessage());
        }
    }

    private void processMark(Parser parser) throws PenguException {
        final String markFormat = "mark <index>";

        int taskIndex = parser.getIntField("", markFormat);
        taskList.markAsDone(taskIndex);

        ui.printMarkTaskMessage(taskList.get(taskIndex));
    }

    private void processUnmark(Parser parser) throws PenguException {
        final String unmarkFormat = "unmark <index>";

        int taskIndex = parser.getIntField("", unmarkFormat);
        taskList.markAsUndone(taskIndex);

        ui.printUnmarkTaskMessage(taskList.get(taskIndex));
    }

    private void processDelete(Parser parser) throws PenguException {
        final String deleteFormat = "delete <index>";
        int taskIndex = parser.getIntField("", deleteFormat);

        ui.printDeleteTaskMessage(taskList.get(taskIndex), taskList);
        taskList.remove(taskIndex);
    }

    private void processTodo(Parser parser) throws PenguException {
        final String todoFormat = "todo <description>";

        String taskDesc = parser.getField("", todoFormat);

        Todo todo = new Todo(taskDesc, false);
        taskList.add(todo);
        ui.printAddTaskMessage(todo, taskList);
    }

    private void processDeadline(Parser parser) throws PenguException {
        final String deadlineFormat = "deadline <description> /by <by>";

        String description = parser.getField(" /by ", deadlineFormat);
        String byString = parser.getField("", deadlineFormat);

        LocalDateTime by = DateTimeParser.fromDateTimeString(byString);

        Deadline deadline = new Deadline(description, false, by);
        taskList.add(deadline);
        ui.printAddTaskMessage(deadline, taskList);
    }

    private void processEvent(Parser parser) throws PenguException {
        final String eventFormat = "event <description> /from <from> /to <to>";

        String description = parser.getField(" /from ", eventFormat);
        LocalDateTime from = parser.getDateTimeField(" /to ", eventFormat);
        LocalDateTime to = parser.getDateTimeField("", eventFormat);

        Event event = new Event(description, false, from, to);
        taskList.add(event);
        ui.printAddTaskMessage(event, taskList);
    }
}
