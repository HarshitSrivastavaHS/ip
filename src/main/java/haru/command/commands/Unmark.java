package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.parser.Parser;
import haru.task.Task;
import haru.ui.Ui;

import java.util.ArrayList;

public class Unmark implements Command {

    private final Parser parser;
    private ArrayList<Task> tasks;

    public Unmark(Parser parser, ArrayList<Task> tasks) {
        this.parser = parser;
        this.tasks = tasks;
    }

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
