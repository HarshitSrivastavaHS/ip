package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.task.Task;
import haru.ui.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Find implements Command {
    private final String SYNTAX = "find <keyword/phrase>";
    private final ArrayList<Task> tasks;

    public Find(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void exec(String args) throws HaruException {
        if (args.trim().isEmpty()) {
            Ui.incorrectCommandUsage(SYNTAX);
        }

        ArrayList<Task> filteredTasks = (ArrayList<Task>) tasks.stream().filter((t)->t.getDescription().toLowerCase().contains(args.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));

        String filteredData = "";
        Task[] filteredCopy = filteredTasks.toArray(Task[]::new);
        int counter = 0;
        for (Task data : filteredCopy) {
            String task = data.getFormattedTask();
            filteredData += "\t" + ++counter + ". " + task + "\n";
        }
        Ui.printFormattedReply(filteredCopy.length == 0 ? "No match found.\n" : "Here are the matching tasks in your list:\n" + filteredData);;
    }
}
