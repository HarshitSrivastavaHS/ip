package haru.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task() {
        description = "";
        isDone = false;
    }

    Task(String description) {
        setDescription(description);
        unmarkDone();
    }

    Task(String description, boolean isDone) {
        setDescription(description);
        this.isDone = isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public boolean getTaskStatus() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getFormattedTask() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public String getSaveFormat() {
        return this.isDone + "<|>" + this.description;
    }
}
