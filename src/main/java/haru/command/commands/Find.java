package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.task.Task;
import haru.ui.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a command that searches for tasks containing a given keyword or phrase.
 *
 * The search is case-insensitive and is performed on the description of each task.
 * Matching tasks are displayed in a numbered list format.
 */
public class Find implements Command {
    private final String SYNTAX = "find <keyword/phrase>";
    private final ArrayList<Task> tasks;

    /**
     * Creates a Find command.
     *
     * @param tasks the list of tasks to search within
     */
    public Find(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Executes the find command.
     *
     * Validates that a keyword is provided. If valid, filters tasks whose descriptions
     * contain the keyword (case-insensitive) and displays matching results. If no matches
     * are found, an appropriate message is shown.
     *
     * @param args the keyword or phrase to search for
     * @throws HaruException never thrown in this implementation
     */
    @Override
    public void exec(String args) throws HaruException {
        if (args.trim().isEmpty()) {
            Ui.incorrectCommandUsage(SYNTAX);
        }

        ArrayList<Task> filteredTasks = tasks.stream().filter((t) -> t.getDescription().toLowerCase().contains(args.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));

        String filteredData = "";
        Task[] filteredCopy = filteredTasks.toArray(Task[]::new);
        int counter = 0;
        for (Task data : filteredCopy) {
            String task = data.getFormattedTask();
            filteredData += "\t" + ++counter + ". " + task + "\n";
        }
        Ui.printFormattedReply(filteredCopy.length == 0 ? "No match found.\n" : "Here are the matching tasks in your list:\n" + filteredData);
        ;
    }
}
