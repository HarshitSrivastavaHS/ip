package haru.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getFormattedTask() {
        return "[T]"+super.getFormattedTask();
    }
}
