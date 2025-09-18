package haru.task;

public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getFormattedTask() {
        return "[E]" + super.getFormattedTask() + " (Event from: " + this.startTime + ", to " + this.endTime + ")";
    }

    public String getSaveFormat() {
        return "E<|>" +super.getSaveFormat() + "<|>" +startTime + "<|>" + endTime;
    }
}
