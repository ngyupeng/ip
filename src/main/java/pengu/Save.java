package pengu;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import pengu.exception.SaveFileException;
import pengu.task.Task;
import pengu.task.Deadline;
import pengu.task.Event;
import pengu.task.TaskList;
import pengu.task.Todo;

/**
 * Class to read and write to save file to persist data over runs.
 */
public class Save {
    public static final String SAVE_FILE_PATH = "./data/pengu.txt";

    public Save() throws SaveFileException {
        Path path = Paths.get(SAVE_FILE_PATH);
        if (!Files.exists(path)) {
            createDirectory();
            createFile();
        }
    }

    /**
     * Save the task list in human-readable format in the save file.
     * @param taskList Task list to be saved.
     */
    public void save(TaskList taskList) throws SaveFileException {
        try {
            FileWriter writer = new FileWriter(SAVE_FILE_PATH);
            writer.write(taskList.toSaveFileFormat());
            writer.close();
        } catch (IOException e) {
            throw new SaveFileException("Failed to write to " + SAVE_FILE_PATH
                    + "\n" + e.getMessage());
        }
    }

    /**
     * Returns the task list as saved in the save file.
     * @return Task list loaded from the save file.
     */
    public TaskList load() throws SaveFileException {
        try {
            File file = new File(SAVE_FILE_PATH);
            Scanner sc = new Scanner(file);
            TaskList taskList = new TaskList();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = taskFromSaveFileFormat(line);
                taskList.add(task);
            }
            return taskList;
        } catch (IOException e) {
            throw new SaveFileException("Failed to load from " + SAVE_FILE_PATH
                    + "\n" + e.getMessage());
        }
    }

    private void createDirectory() throws SaveFileException {
        Path path = Paths.get(SAVE_FILE_PATH).getParent();

        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            throw new SaveFileException("Failed to create directory ./data\n" + e.getMessage());
        }
    }

    private void createFile() throws SaveFileException {
        Path path = Paths.get(SAVE_FILE_PATH);

        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new SaveFileException("Failed to create file " + SAVE_FILE_PATH
                    + "\n" + e.getMessage());
        }
    }

    private Task taskFromSaveFileFormat(String line) throws SaveFileException {
        if (line.startsWith("T |")) {
            return Todo.fromSaveFileFormat(line);
        } else if (line.startsWith("D |")) {
            return Deadline.fromSaveFileFormat(line);
        } else if (line.startsWith("E | ")) {
            return Event.fromSaveFileFormat(line);
        } else {
            throw new SaveFileException("Line with unknown format found in save file:\n" + line);
        }
    }
}
