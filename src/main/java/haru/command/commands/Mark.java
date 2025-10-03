package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.parser.Parser;
import haru.task.Task;
import haru.ui.Ui;

import java.util.ArrayList;

/**
 * Command that marks a specific task in the list as completed.
 *
 * The task is identified by its index, which is validated using
 * {@link Parser#validateIndex(String, String)}. If valid, the corresponding
 * task is marked done and a confirmation message is shown.
 */
public class Mark implements Command {

    private final String SYNTAX = "mark <list item number>";
    private final Parser parser;
    private ArrayList<Task> tasks;

    /**
     * Creates a new Mark command.
     *
     * @param parser parser used to validate task index
     * @param tasks the list of tasks from which an item will be marked as done
     */
    public Mark(Parser parser, ArrayList<Task> tasks) {
        this.parser = parser;
        this.tasks = tasks;
    }

    /**
     * Marks the specified task as done if the index is valid.
     *
     * @param args the index of the task to be marked, as a string
     * @throws HaruException if thrown internally by input validation
     */
    @Override
    public void exec(String args) throws HaruException {
        if (args.trim().isEmpty()) {
            Ui.incorrectCommandUsage(SYNTAX);
        }
        int index = parser.validateIndex(args, "Invalid List Item");
        if (index == -1) {
            return;
        }
        tasks.get(index).markDone();
        String formattedString = tasks.get(index).getFormattedTask();
        Ui.printFormattedReply("Task Marked as done:\n\t" + formattedString);
    }
}
