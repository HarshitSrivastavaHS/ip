package haru.task;

/**
 * Represents a task with a description and completion status.
 *
 * This class serves as a base class for specific task types such as
 * {@link Todo}, {@link Deadline}, or {@link Event}.
 */
public abstract class Task {

    private String description;

    private boolean isDone;


    /**
     * Creates a new task with an empty description and marked as not done.
     */
    public Task() {
        description = "";
        isDone = false;
    }

    /**
     * Creates a new task with the given description and marked as not done.
     *
     * @param description the description of the task
     */
    Task(String description) {
        setDescription(description);
        unmarkDone();
    }

    /**
     * Creates a new task with the given description and completion status.
     *
     * @param description the description of the task
     * @param isDone      true if the task is completed, false otherwise
     */
    Task(String description, boolean isDone) {
        setDescription(description);
        this.isDone = isDone;
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks this task as undone.
     */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Returns the completion status of this task.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean getTaskStatus() {
        return isDone;
    }

    /**
     * Sets the description of this task.
     *
     * @param description the new description for the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the description of this task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a formatted string representation of the task suitable for display.
     *
     * @return formatted task string
     */
    public String getFormattedTask() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Returns a string representing the task for saving to storage.
     *
     * @return task in save format as "true<|>description of the task"
     */
    public String getSaveFormat() {
        return this.isDone + "<|>" + this.description;
    }
}
