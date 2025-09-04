public class Deadline extends Task {
    private String deadlineTime;

    Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String getFormattedTask() {
        return "[D]" + super.getFormattedTask() + " (Deadline: " + this.deadlineTime + ")";
    }
}
