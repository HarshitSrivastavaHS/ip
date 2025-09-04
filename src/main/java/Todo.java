public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    public String getFormattedTask() {
        return "[T]"+super.getFormattedTask();
    }
}
