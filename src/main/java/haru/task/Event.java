package haru.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    private String startTime;
    private String endTime;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime.trim();
        this.endTime = endTime.trim();
        this.startDateTime = LocalDateTime.parse(startTime.trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        this.endDateTime = LocalDateTime.parse(endTime.trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        if (this.startDateTime.isAfter(this.endDateTime) || this.startDateTime.isEqual(this.endDateTime)) {
            throw new IllegalArgumentException("Event start time must be before end time.");
        }

    }

    public Event(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDateTime = LocalDateTime.parse(startTime.trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        this.endDateTime = LocalDateTime.parse(endTime.trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        if (this.startDateTime.isAfter(this.endDateTime) || this.startDateTime.isEqual(this.endDateTime)) {
            throw new IllegalArgumentException("Event start time must be before end time.");
        }
    }

    @Override
    public String getFormattedTask() {
        return "[E]" + super.getFormattedTask() + " (Event from: " + startDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma")) + ", to: " + endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma")) + ")";
    }

    public String getSaveFormat() {
        return "E<|>" + super.getSaveFormat() + "<|>" + startTime + "<|>" + endTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}
