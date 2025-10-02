package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.task.Deadline;
import haru.task.Event;
import haru.task.Task;
import haru.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindDate implements Command {
    private final String SYNTAX = "finddate <d/m/yyyy>";
    private final ArrayList<Task> tasks;

    public FindDate(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void exec(String args) throws HaruException {
        LocalDate queryDate;
        try {
            queryDate = LocalDate.parse(args.trim(), DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            Ui.incorrectCommandUsage(SYNTAX);
            return;
        }


        ArrayList<Task> filteredTasks = tasks.stream()
                .filter(t -> (t instanceof Deadline d && d.getDueDateTime().toLocalDate().isEqual(queryDate))
                        || (t instanceof Event e &&
                        !e.getStartDateTime().toLocalDate().isAfter(queryDate) &&
                        !e.getEndDateTime().toLocalDate().isBefore(queryDate)))
                .collect(Collectors.toCollection(ArrayList::new));


        String filteredData = "";
        Task[] filteredCopy = filteredTasks.toArray(Task[]::new);
        int counter = 0;
        for (Task data : filteredCopy) {
            String task = data.getFormattedTask();
            filteredData += "\t" + ++counter + ". " + task + "\n";
        }
        Ui.printFormattedReply(filteredCopy.length == 0 ? "No match found.\n" : "Here are the deadlines/tasks on the following date:\n" + filteredData);;
    }
}
