package haru.ui;

import haru.exception.HaruException;
import haru.task.Task;

/**
 * Provides common methods for user interaction and displaying
 * formatted messages in the terminal.
 *
 * This includes greetings, prompts, separators, and task-related messages.
 */
public class Ui {

    /** ASCII art logo of Haru displayed at greeting. */
    private final static String LOGO = """
             \t _____                                                                        _____
            \t( ___ )                                                                      ( ___ )
            \t |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   |
            \t |   | __/\\\\\\________/\\\\\\____________________________________________________ |   |
            \t |   | __\\/\\\\\\_______\\/\\\\\\___________________________________________________ |   |
            \t |   | ___\\/\\\\\\_______\\/\\\\\\__________________________________________________ |   |
            \t |   | ____\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\__/\\\\\\\\\\\\\\\\\\_____/\\\\/\\\\\\\\\\\\\\___/\\\\\\____/\\\\\\______ |   |
            \t |   | _____\\/\\\\\\/////////\\\\\\_\\////////\\\\\\___\\/\\\\\\/////\\\\\\_\\/\\\\\\___\\/\\\\\\_____ |   |
            \t |   | ______\\/\\\\\\_______\\/\\\\\\___/\\\\\\\\\\\\\\\\\\\\__\\/\\\\\\___\\///__\\/\\\\\\___\\/\\\\\\____ |   |
            \t |   | _______\\/\\\\\\_______\\/\\\\\\__/\\\\\\/////\\\\\\__\\/\\\\\\_________\\/\\\\\\___\\/\\\\\\___ |   |
            \t |   | ________\\/\\\\\\_______\\/\\\\\\_\\//\\\\\\\\\\\\\\\\/\\\\_\\/\\\\\\_________\\//\\\\\\\\\\\\\\\\\\___ |   |
            \t |   | _________\\///________\\///___\\////////\\//__\\///___________\\/////////___ |   |
            \t |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___|
            \t(_____)                                                                      (_____)
            """;

    /** A separator line used for formatting console output. */
    private final static String SEPARATOR_LINE = "____________________________________________________________________________________";

    /**
     * Greets the user with a welcome message and the ASCII art logo.
     */
    public static void greet() {
        printFormattedReply("Hello, I'm\n" + LOGO + "\nWhat can i do for you?");
    }

    /**
     * Prints a formatted reply with separator lines before and after.
     *
     * @param reply The message to display to the user.
     */
    public static void printFormattedReply(String reply) {
        printLine();
        System.out.println(getFormattedReply(reply));
        printLine();
    }

    /** Prints a separator line to the console. */
    public static void printLine() {
        System.out.println("\t" + SEPARATOR_LINE);
    }

    /**
     * Adds tab indentation to each line of the reply for formatting.
     *
     * @param reply The message to format.
     * @return Formatted string with tabbed lines.
     */
    public static String getFormattedReply(String reply) {
        String formattedReply = "";
        for (String line : reply.trim().split("\n")) {
            formattedReply += "\t" + line + "\n";
        }
        return "\t" + formattedReply.trim();
    }

    /** Prints the prompt symbol for user input. */
    public static void printPrompt() {
        System.out.print("> ");
    }

    /**
     * Throws an exception when the user provides incorrect command usage.
     *
     * @param commandTemplate The correct usage template of the command.
     * @throws HaruException Thrown with a message showing correct usage.
     */
    public static void incorrectCommandUsage(String commandTemplate) throws HaruException {
        throw new HaruException("Incorrect command usage.\n\tCorrect Usage: " + commandTemplate);
    }

    /**
     * Prints a message confirming that a new task has been added.
     *
     * @param taskType Type of the task (e.g., Todo, Deadline, Event).
     * @param data The Task object containing task details.
     */
    public static void printTaskAdd(String taskType, Task data) {
        printFormattedReply("New " + taskType + " added:\n\t" + data.getFormattedTask());
    }
}
