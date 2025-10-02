package haru.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 *
 * An Event is a type of {@link Task} that has a description, completion status,
 * a start date/time, and an end date/time.
 */
public class Event extends Task {
    private String startTime;
    private String endTime;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Constructs a new Event task with the given description, start time, and end time.
     *
     * @param description description of the event
     * @param startTime start time in the format "d/M/yyyy HHmm", e.g., "2/10/2025 0900"
     * @param endTime end time in the format "d/M/yyyy HHmm", e.g., "2/10/2025 1100"
     * @throws IllegalArgumentException if startTime is after or equal to endTime
     */
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

    /**
     * Constructs a new Event task with the given description, completion status,
     * start time, and end time.
     *
     * @param description description of the event
     * @param isDone true if the event is completed, false otherwise
     * @param startTime start time in the format "d/M/yyyy HHmm"
     * @param endTime end time in the format "d/M/yyyy HHmm"
     * @throws IllegalArgumentException if startTime is after or equal to endTime
     */
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

    /**
     * Returns a formatted string representation of the Event suitable for display.
     *
     * Overrides {@link Task#getFormattedTask()} by adding an "[E]" prefix and
     * including start and end date/times.
     *
     * @return formatted event string
     */
    @Override
    public String getFormattedTask() {
        return "[E]" + super.getFormattedTask() + " (Event from: " + startDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma")) + ", to: " + endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma")) + ")";
    }

    /**
     * Returns a string representing the Event for saving to storage.
     *
     * Overrides {@link Task#getSaveFormat()} by adding an "E<|>" prefix
     * and storing the original start and end times.
     *
     * @return event in save format as "E<|>true<|>description<|>startTime<|>endTime"
     */
    @Override
    public String getSaveFormat() {
        return "E<|>" + super.getSaveFormat() + "<|>" + startTime + "<|>" + endTime;
    }

    /**
     * Returns the start date/time of the event as a {@link LocalDateTime} object.
     *
     * @return start date/time
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Returns the end date/time of the event as a {@link LocalDateTime} object.
     *
     * @return end date/time
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}
