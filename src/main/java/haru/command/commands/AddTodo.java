package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.task.Task;
import haru.task.Todo;

import haru.ui.Ui;
import haru.util.Counter;

import java.util.ArrayList;

/**
 * Command to add a {@link Todo} task to the task list.
 *
 * Expects arguments in the format: {@code <description>}.
 */
public class AddTodo implements Command {
    private final String SYNTAX = "todo <description";
    private ArrayList<Task> tasks;
    private Counter currentItemCount;

    /**
     * Constructs an {@code AddTodo} command.
     *
     * @param tasks the task list to add new todo tasks to
     * @param currentItemCount the counter tracking the number of tasks
     */
    public AddTodo(ArrayList<Task> tasks, Counter currentItemCount) {
        this.tasks = tasks;
        this.currentItemCount = currentItemCount;
    }

    /**
     * Executes the command by creating a new {@link Todo} task and adding it to the list.
     *
     * <p>If the arguments are empty, an error message is printed to the user via {@link Ui}.</p>
     *
     * @param args the command arguments in the form {@code <description>}
     * @throws HaruException if the command usage is incorrect
     */
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
