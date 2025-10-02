package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.task.Deadline;
import haru.task.Task;
import haru.task.Todo;
import haru.ui.Ui;
import haru.util.Counter;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AddDeadline implements Command {
    private final String SYNTAX = "deadline <description> /by <deadline>";
    private ArrayList<Task> tasks;
    private Counter currentItemCount;

    public AddDeadline(ArrayList<Task> tasks, Counter currentItemCount) {
        this.tasks = tasks;
        this.currentItemCount = currentItemCount;
    }


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