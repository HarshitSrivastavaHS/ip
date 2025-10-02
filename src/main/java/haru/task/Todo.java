package haru.task;

/**
 * Represents a Todo task.
 *
 * A Todo is a type of {@link Task} that has only a description and a completion status,
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo with the given description.
     *
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new Todo with the given description and completion status.
     *
     * @param description description of the task
     * @param isDone      true if the task is completed, false otherwise
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a formatted string representation of the Todo suitable for display.
     *
     * Overrides {@link Task#getFormattedTask()} by adding a "[T]" prefix.
     *
     * @return formatted task string
     */
    @Override
    public String getFormattedTask() {
        return "[T]" + super.getFormattedTask();
    }

    /**
     * Returns a string representing the Todo for saving to storage.
     *
     * Overrides {@link Task#getSaveFormat()} by adding a "T<|>" prefix.
     *
     * @return task in save format as "T<|>true<|>description of the task"
     */
    @Override
    public String getSaveFormat() {
        return "T<|>" + super.getSaveFormat();
    }
}
