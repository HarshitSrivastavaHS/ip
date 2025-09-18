package haru.datasave;

import haru.task.Deadline;
import haru.task.Task;
import haru.task.Todo;
import haru.task.Event;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketOption;
import java.util.Arrays;
import java.util.Scanner;


public class SaveLoadManager {
    private final String SAVE_FOLDER = "HaruData";
    private final String SAVE_FILE = "savefile.txt";

    public int loadData(Task[] tasks) {
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
                switch(lineComponents[0]) {
                case "T":
                    tasks[i++] = new Todo(lineComponents[2], Boolean.parseBoolean(lineComponents[1]));
                    break;
                case "E":
                    tasks[i++] = new Event(lineComponents[2], Boolean.parseBoolean(lineComponents[1]), lineComponents[3], lineComponents[4]);
                    break;
                case "D":
                    tasks[i++] = new Deadline(lineComponents[2], Boolean.parseBoolean(lineComponents[1]), lineComponents[3]);;
                    break;
                }
            }
            return i;
        } catch (FileNotFoundException e) {
            System.out.println("Error in loading data");
            e.printStackTrace();
            return i;
        }
    }

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

    private String getString(Task[] tasks) {
        String data = "";

        for (Task task: tasks) {
            data += task.getSaveFormat() + "\n";
        }

        return data;
    }
}
