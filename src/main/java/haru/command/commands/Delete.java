package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.parser.Parser;
import haru.task.Task;
import haru.ui.Ui;
import haru.util.Counter;

import java.util.ArrayList;

/**
 * Represents a command that deletes a task from the task list.
 *
 * This command expects an index as an argument. The index is validated
 * using {@link Parser#validateIndex(String, String)}. If the index is valid,
 * the corresponding task is removed from the list and the task count is decremented.
 */
public class Delete implements Command {

    private final Parser parser;
    private final ArrayList<Task> tasks;
    private final Counter currentItemCount;

    /**
     * Constructor for Delete command.
     *
     * @param parser           the parser used to validate the task index
     * @param tasks            the list of tasks from which a task will be deleted
     * @param currentItemCount a counter that tracks the current number of tasks
     */
    public Delete(Parser parser, ArrayList<Task> tasks, Counter currentItemCount) {
        this.parser = parser;
        this.tasks = tasks;
        this.currentItemCount = currentItemCount;
    }

    /**
     * Executes the delete command.
     *
     * Validates the provided index. If valid, removes the corresponding task
     * from the task list, decrements the task counter, and prints a confirmation message.
     *
     * @param args the index of the task to delete (1-based index as a string)
     * @throws HaruException never thrown in this implementation
     */
    @Override
    public void exec(String args) throws HaruException {
        int index = parser.validateIndex(args, "Invalid List Item");
        if (index == -1) {
            return;
        }
        Task task = tasks.get(index);
        tasks.remove(index);
        currentItemCount.value--;
        Ui.printFormattedReply("I've removed the task:\n\t" + task.getFormattedTask());
    }
}
