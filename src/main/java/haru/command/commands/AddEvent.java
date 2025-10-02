package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.task.Event;
import haru.task.Task;
import haru.ui.Ui;
import haru.util.Counter;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Command to add an {@link Event} task to the task list.
 *
 * Expects arguments in the format: {@code <description> /from <startTime> /to <endTime>}.
 */
public class AddEvent implements Command {
    private final String SYNTAX = "event <description> /from <startTime> /to <end time>";
    private ArrayList<Task> tasks;
    private Counter currentItemCount;

    /**
     * Constructs an {@code AddEvent} command.
     *
     * @param tasks the task list to add new event tasks to
     * @param currentItemCount the counter tracking the number of tasks
     */
    public AddEvent(ArrayList<Task> tasks, Counter currentItemCount) {
        this.tasks = tasks;
        this.currentItemCount = currentItemCount;
    }

    /**
     * Executes the command by creating a new {@link Event} task and adding it to the list.
     *
     * If the arguments do not match the expected format, the start time is after the end time,
     * or the date/time format is invalid, an error message is printed to the user via {@link Ui}.
     *
     * @param args the command arguments in the form {@code <description> /from <startTime> /to <endTime>}
     * @throws HaruException if the command usage is incorrect
     */
    @Override
    public void exec(String args) throws HaruException {
        int eventStartDelimiter = args.indexOf("/from");
        int eventEndDelimiter = args.indexOf("/to");
        if (eventStartDelimiter == -1 || eventEndDelimiter == -1) {
            Ui.incorrectCommandUsage(SYNTAX);
            return;
        }
        String description = args.substring(0, eventStartDelimiter).trim();
        if (description.isEmpty()) {
            Ui.incorrectCommandUsage(SYNTAX);
        }
        String eventStartTime = args.substring(eventStartDelimiter + 5, eventEndDelimiter);
        String eventEndTime = args.substring(eventEndDelimiter + 3);
        try {
            Event eventTask = new Event(description.trim(), eventStartTime, eventEndTime);
            tasks.add(eventTask);
            currentItemCount.value++;
            Ui.printTaskAdd("Event", eventTask);
        } catch (DateTimeParseException e) {
            Ui.printFormattedReply("Invalid date/time format. Use d/m/yyyy HHmm");
        } catch (IllegalArgumentException e) {
            Ui.printFormattedReply(e.getMessage());
        }

    }
}