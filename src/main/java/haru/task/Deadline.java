package haru.task;

public class Deadline extends Task {
    private String deadlineTime;

    public Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime.trim();
    }

    @Override
    public String getFormattedTask() {
        return "[D]" + super.getFormattedTask() + " (Deadline: " + this.deadlineTime + ")";
    }
}
