package haru.task;

public class Deadline extends Task {
    private String deadlineTime;

    public Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime.trim();
    }

    public Deadline(String description, boolean isDone, String deadlineTime) {
        super(description, isDone);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String getFormattedTask() {
        return "[D]" + super.getFormattedTask() + " (Deadline: " + this.deadlineTime + ")";
    }

    public String getSaveFormat() {
        return "D<|>" +super.getSaveFormat() + "<|>" +deadlineTime;
    }
}
