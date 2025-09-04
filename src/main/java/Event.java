public class Event extends Task{
    private String startTime;
    private String endTime;

    Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getFormattedTask() {
        return "[E]" + super.getFormattedTask() + " (Event from: " + this.startTime + ", to " + this.endTime + ")";
    }
}
