public class Task {
    private String description;
    private boolean isDone;

    Task() {
        description = "";
        isDone = false;
    }

    Task(String description) {
        setDescription(description);
        unmarkDone();
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
}
