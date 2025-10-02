package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.task.Task;
import haru.task.Todo;

import haru.ui.Ui;
import haru.util.Counter;

import java.util.ArrayList;

public class AddTodo implements Command {
    private final String SYNTAX = "todo <description";
    private ArrayList<Task> tasks;
    private Counter currentItemCount;

    public AddTodo(ArrayList<Task> tasks, Counter currentItemCount) {
        this.tasks = tasks;
        this.currentItemCount = currentItemCount;
    }


    @Override
    public void exec(String args) throws HaruException {
        if (args.trim().isEmpty()) {
            Ui.incorrectCommandUsage(SYNTAX);
        }

        Todo todoTask = new Todo(args);
        tasks.add(todoTask);
        currentItemCount.value++;
        Ui.printTaskAdd("Todo", todoTask);
    }
}
