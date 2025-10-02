package haru.ui;

import haru.exception.HaruException;
import haru.task.Task;

public class Ui {

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
    private final static String SEPARATOR_LINE = "____________________________________________________________________________________";


    public static void greet() {
        printFormattedReply("Hello, I'm\n" + LOGO + "\nWhat can i do for you?");
    }

    public static void printFormattedReply(String reply) {
        printLine();
        System.out.println(getFormattedReply(reply));
        printLine();
    }

    public static void printLine() {
        System.out.println("\t" + SEPARATOR_LINE);
    }

    public static String getFormattedReply(String reply) {
        String formattedReply = "";
        for (String line : reply.trim().split("\n")) {
            formattedReply += "\t" + line + "\n";
        }
        return "\t" + formattedReply.trim();
    }

    public static void printPrompt() {
        System.out.print("> ");
    }

    public static void incorrectCommandUsage(String commandTemplate) throws HaruException {
        throw new HaruException("Incorrect command usage.\n\tCorrect Usage: " + commandTemplate);
    }

    public static void printTaskAdd(String taskType, Task data) {
        printFormattedReply("New " + taskType + " added:\n\t" + data.getFormattedTask());
    }


}
