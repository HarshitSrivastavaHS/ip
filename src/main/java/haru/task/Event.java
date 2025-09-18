package haru.task;

public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime.trim();
        this.endTime = endTime.trim();
    }

    @Override
    public String getFormattedTask() {
        return "[E]" + super.getFormattedTask() + " (Event from: " + this.startTime + ", to: " + this.endTime + ")";
    }
}
