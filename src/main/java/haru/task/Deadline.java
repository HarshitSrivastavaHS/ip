package haru.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 *
 * A Deadline is a type of {@link Task} that has a description, completion status,
 * and a due date/time.
 */
public class Deadline extends Task {
    private final String deadlineTime;
    private final LocalDateTime dueDateTime;

    /**
     * Constructs a new Deadline task with the given description and deadline time.
     *
     * @param description description of the task
     * @param deadlineTime deadline in the format "d/M/yyyy HHmm", e.g., "2/10/2025 2359"
     */
    public Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime.trim();
        this.dueDateTime = LocalDateTime.parse(deadlineTime.trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Constructs a new Deadline task with the given description, completion status,
     * and deadline time.
     *
     * @param description description of the task
     * @param isDone true if the task is completed, false otherwise
     * @param deadlineTime deadline in the format "d/M/yyyy HHmm"
     */
    public Deadline(String description, boolean isDone, String deadlineTime) {
        super(description, isDone);
        this.deadlineTime = deadlineTime;
        this.dueDateTime = LocalDateTime.parse(deadlineTime.trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Returns a formatted string representation of the Deadline suitable for display.
     *
     * Overrides {@link Task#getFormattedTask()} by adding a "[D]" prefix and
     * the formatted deadline.
     *
     * @return formatted task string including deadline
     */
    @Override
    public String getFormattedTask() {
        return "[D]" + super.getFormattedTask() + " (Deadline: " + dueDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma")) + ")";
    }

    /**
     * Returns a string representing the Deadline for saving to storage.
     *
     * Overrides {@link Task#getSaveFormat()} by adding a "D<|>" prefix and
     * storing the original deadline string.
     *
     * @return task in save format as "D<|>true<|>description<|>deadlineTime"
     */
    @Override
    public String getSaveFormat() {
        return "D<|>" + super.getSaveFormat() + "<|>" + deadlineTime;
    }

    /**
     * Returns the due date and time as a {@link LocalDateTime} object.
     *
     * @return due date/time of the task
     */
    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }
}
