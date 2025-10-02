package haru.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    private final String deadlineTime;
    private final LocalDateTime dueDateTime;


    public Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime.trim();
        this.dueDateTime = LocalDateTime.parse(deadlineTime.trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    public Deadline(String description, boolean isDone, String deadlineTime) {
        super(description, isDone);
        this.deadlineTime = deadlineTime;
        this.dueDateTime = LocalDateTime.parse(deadlineTime.trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    @Override
    public String getFormattedTask() {
        return "[D]" + super.getFormattedTask() + " (Deadline: " + dueDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma")) + ")";
    }

    public String getSaveFormat() {
        return "D<|>" + super.getSaveFormat() + "<|>" + deadlineTime;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }
}
