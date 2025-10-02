package haru.datasave;

import haru.task.Deadline;
import haru.task.Task;
import haru.task.Todo;
import haru.task.Event;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages saving and loading of tasks to and from persistent storage.
 *
 * This class handles creating the save folder and file, reading saved
 * tasks from a text file, and writing tasks to the file. Tasks are stored
 * in a plain-text format where each line represents one task, prefixed by
 * its type ("T" for Todo, "D" for Deadline, "E" for Event).
 */
public class SaveLoadManager {
    private final String SAVE_FOLDER = "HaruData";
    private final String SAVE_FILE = "savefile.txt";

    /**
     * Loads tasks from the save file into the provided list.
     *
     * If the folder or file does not exist, it will create the folder
     * and return 0 (no tasks loaded).
     *
     * @param tasks the list to populate with loaded tasks
     * @return the number of tasks successfully loaded
     */
    public int loadData(ArrayList<Task> tasks) {
        File folder = new File(SAVE_FOLDER);

        if (!folder.exists()) {
            folder.mkdir();
        }

        File saveFile = new File(folder, SAVE_FILE);

        if (!saveFile.exists()) {
            return 0;
        }
        int i = 0;
        try {
            Scanner sc = new Scanner(saveFile);
            while (sc.hasNextLine()) {
                String[] lineComponents = sc.nextLine().split("<\\|>");
                switch (lineComponents[0]) {
                case "T":
                    tasks.add(new Todo(lineComponents[2], Boolean.parseBoolean(lineComponents[1])));
                    break;
                case "E":
                    tasks.add(new Event(lineComponents[2], Boolean.parseBoolean(lineComponents[1]), lineComponents[3], lineComponents[4]));
                    break;
                case "D":
                    tasks.add(new Deadline(lineComponents[2], Boolean.parseBoolean(lineComponents[1]), lineComponents[3]));
                    break;
                }
                i++;
            }
            return i;
        } catch (FileNotFoundException e) {
            System.out.println("Error in loading data");
            e.printStackTrace();
            return i;
        }
    }

    /**
     * Saves an array of tasks to the save file.
     *
     * @param tasks the array of tasks to save
     */
    public void saveData(Task[] tasks) {
        try {
            FileWriter saveWriter = new FileWriter(SAVE_FOLDER + "/" + SAVE_FILE);
            String data = getString(tasks);
            saveWriter.write(data);
            saveWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving to file");
            e.printStackTrace();
        }
    }

    /**
     * Converts an array of tasks into a string suitable for saving.
     *
     * Each task is converted to its save format and is stored in a new line.
     *
     * @param tasks the array of tasks to convert
     * @return a string representing all tasks in save format
     */
    private String getString(Task[] tasks) {
        String data = "";

        for (Task task : tasks) {
            data += task.getSaveFormat() + "\n";
        }

        return data;
    }
}
