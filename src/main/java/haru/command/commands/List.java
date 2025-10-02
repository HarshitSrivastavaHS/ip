package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.task.Task;
import haru.ui.Ui;

import java.util.ArrayList;

public class List implements Command {

    private ArrayList<Task> tasks;

    public List(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void exec(String args) throws HaruException {
        String taskData = "";
        Task[] tasksCopy = tasks.toArray(Task[]::new);
        int counter = 0;
        for (Task data : tasksCopy) {
            String task = data.getFormattedTask();
            taskData += "\t" + ++counter + ". " + task + "\n";
        }
        Ui.printFormattedReply(tasksCopy.length == 0 ? "Your list is empty\n" : "Here is your list:\n" + taskData);
    }
}
