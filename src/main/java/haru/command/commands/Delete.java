package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.parser.Parser;
import haru.task.Task;
import haru.ui.Ui;
import haru.util.Counter;

import java.util.ArrayList;

public class Delete implements Command {

    private final Parser parser;
    private final ArrayList<Task> tasks;
    private final Counter currentItemCount;

    public Delete(Parser parser, ArrayList<Task> tasks, Counter currentItemCount) {
        this.parser = parser;
        this.tasks = tasks;
        this.currentItemCount = currentItemCount;
    }

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
