package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.task.Deadline;
import haru.task.Task;
import haru.ui.Ui;
import haru.util.Counter;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Command to add a {@link Deadline} task to the task list.
 *
 * Expects arguments in the format: {@code <description> /by <deadline>}.
 */
public class AddDeadline implements Command {
    private final String SYNTAX = "deadline <description> /by <deadline>";
    private ArrayList<Task> tasks;
    private Counter currentItemCount;

    /**
     * Constructs an {@code AddDeadline} command.
     *
     * @param tasks the task list to add new deadline tasks to
     * @param currentItemCount the counter tracking the number of tasks
     */
    public AddDeadline(ArrayList<Task> tasks, Counter currentItemCount) {
        this.tasks = tasks;
        this.currentItemCount = currentItemCount;
    }

    /**
     * Executes the command by creating a new {@link Deadline} task and adding it to the list.
     *
     * If the arguments do not match the expected format or the date/time is invalid, an
     * error message is printed to the user via {@link Ui}.
     *
     * @param args the command arguments in the form {@code <description> /by <deadline>}
     * @throws HaruException if the command usage is incorrect
     */
    @Override
    public void exec(String args) throws HaruException {
        int delimiter = args.indexOf("/by");
        if (delimiter == -1) {
            Ui.incorrectCommandUsage(SYNTAX);
        }
        String description = args.substring(0, delimiter);
        if (description.isEmpty()) {
            Ui.incorrectCommandUsage(SYNTAX);
        }
        String deadline = args.substring(delimiter + 3);
        try {
            Deadline deadlineTask = new Deadline(description.trim(), deadline);
            tasks.add(deadlineTask);
            currentItemCount.value++;
            Ui.printTaskAdd("Deadline", deadlineTask);
        } catch (DateTimeParseException e) {
            Ui.printFormattedReply("Invalid date/time format. Use d/m/yyyy HHmm");
        }
    }
}