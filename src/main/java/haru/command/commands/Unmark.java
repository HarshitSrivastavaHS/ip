package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.parser.Parser;
import haru.task.Task;
import haru.ui.Ui;

import java.util.ArrayList;

/**
 * Command that marks a specific task in the list as not completed.
 *
 * The task is located using its index via
 * {@link Parser#validateIndex(String, String)}. If the index is valid,
 * the task is unmarked and a confirmation message is displayed.
 */
public class Unmark implements Command {

    private final Parser parser;
    private ArrayList<Task> tasks;

    /**
     * Creates a new Unmark command.
     *
     * @param parser the parser used to validate the task index
     * @param tasks the list of tasks from which an item will be unmarked
     */
    public Unmark(Parser parser, ArrayList<Task> tasks) {
        this.parser = parser;
        this.tasks = tasks;
    }

    /**
     * Marks the specified task as not done if the index is valid.
     *
     * @param args the index of the task to be unmarked, as a string
     * @throws HaruException if thrown internally by input validation
     */
    @Override
    public void exec(String args) throws HaruException {
        int index = parser.validateIndex(args, "Invalid List Item");
        if (index == -1) {
            return;
        }
        tasks.get(index).unmarkDone();
        String formattedString = tasks.get(index).getFormattedTask();
        Ui.printFormattedReply("\tTask Marked as not done:\n\t" + formattedString);
    }
}
