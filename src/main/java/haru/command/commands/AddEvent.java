package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.task.Deadline;
import haru.task.Event;
import haru.task.Task;
import haru.task.Todo;
import haru.ui.Ui;
import haru.util.Counter;

import java.util.ArrayList;

public class AddEvent implements Command {
    private final String SYNTAX = "event <description> /from <startTime> /to <end time>";
    private ArrayList<Task> tasks;
    private Counter currentItemCount;

    public AddEvent(ArrayList<Task> tasks, Counter currentItemCount) {
        this.tasks = tasks;
        this.currentItemCount = currentItemCount;
    }


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
        Event eventTask = new Event(description, eventStartTime, eventEndTime);
        tasks.add(eventTask);
        currentItemCount.value++;
        Ui.printTaskAdd("Event", eventTask);
    }
}