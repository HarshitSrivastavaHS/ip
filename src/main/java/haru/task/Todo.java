package haru.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getFormattedTask() {
        return "[T]" + super.getFormattedTask();
    }

    @Override
    public String getSaveFormat() {
        return "T<|>" + super.getSaveFormat();
    }
}
