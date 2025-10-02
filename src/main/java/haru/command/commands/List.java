package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.task.Task;
import haru.ui.Ui;

import java.util.ArrayList;

/**
 * Command that displays all tasks currently stored in the task list.
 *
 * The tasks are printed in a formatted numbered list using {@link Ui}.
 * If no tasks are present, an appropriate message is shown instead.
 */
public class List implements Command {

    private ArrayList<Task> tasks;

    /**
     * Creates a new List command with access to the task list.
     *
     * @param tasks the collection of tasks to be listed
     */
    public List(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Displays all current tasks in the order they were added in.
     *
     * Each task is printed with its index and formatted status. If the list
     * is empty, a fallback message is shown instead.
     *
     * @param args not used for this command
     * @throws HaruException never thrown in this implementation
     */
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
