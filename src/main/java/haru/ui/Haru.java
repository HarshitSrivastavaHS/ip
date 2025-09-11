package haru.ui;

import haru.exception.HaruException;
import haru.task.Deadline;
import haru.task.Event;
import haru.task.Task;
import haru.task.Todo;

import java.util.Arrays;
import java.util.Scanner;

public class Haru {

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
    private final static String TODO_SYNTAX = "todo <description";
    private final static String EVENT_SYNTAX = "event <description> /from <startTime> /to <end time>";
    private final static String DEADLINE_SYNTAX = "deadline <description> /by <deadline>";

    private final Task[] tasks;
    private int currentItemNo;

    Haru() {
        greet();
        tasks = new Task[100];
        currentItemNo = 0;
        handleCommands();
    }

    private void greet() {
        printFormattedReply("Hello, I'm\n" + LOGO + "\nWhat can i do for you?");
    }

    private void handleCommands() {
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");
        while (sc.hasNextLine()) {
            String commandLine = sc.nextLine().trim();
            String command = commandLine.substring(0, commandLine.contains(" ") ? commandLine.indexOf(" ") : commandLine.length());
            String args = commandLine.equals(command) ? "" : commandLine.substring(commandLine.indexOf(" ") + 1);
            try {
                switch (command) {
                case "bye":
                    bye();
                    break;
                case "list":
                    list();
                    break;
                case "mark":
                    listMark(args);
                    break;
                case "unmark":
                    listUnmark(args);
                    break;
                case "todo":
                    addTodo(args);
                    break;
                case "deadline":
                    addDeadline(args);
                    break;
                case "event":
                    addEvent(args);
                    break;
                default:
                    throw new HaruException("Invalid Command. Not quite sure what you mean by \"" + commandLine + "\" O_o");
                }
            } catch (HaruException e) {
                printFormattedReply("Error: " + e.getMessage());
            }
            System.out.print("> ");
        }
    }

    private void addTodo(String data) throws HaruException {
        if (data.trim().isEmpty()) {
            incorrectCommandUsage(TODO_SYNTAX);
        }
        tasks[currentItemNo++] = new Todo(data);
        printFormattedReply("New haru.task.Todo added:\n\t" + tasks[currentItemNo - 1].getFormattedTask());
    }

    private void addDeadline(String data) throws HaruException {
        int delimiter = data.indexOf("/by");
        if (delimiter == -1) {
            incorrectCommandUsage(DEADLINE_SYNTAX);
        }
        String description = data.substring(0, delimiter);
        if (description.isEmpty()) {
            incorrectCommandUsage(DEADLINE_SYNTAX);
        }
        String deadline = data.substring(delimiter + 3);
        tasks[currentItemNo++] = new Deadline(description, deadline);
        printFormattedReply("New haru.task.Deadline added:\n\t" + tasks[currentItemNo - 1].getFormattedTask());
    }

    private void addEvent(String data) throws HaruException {
        int eventStartDelimiter = data.indexOf("/from");
        int eventEndDelimiter = data.indexOf("/to");
        if (eventStartDelimiter == -1 || eventEndDelimiter == -1) {
            incorrectCommandUsage(EVENT_SYNTAX);
            return;
        }
        String description = data.substring(0, eventStartDelimiter).trim();
        if (description.isEmpty()) {
            incorrectCommandUsage(EVENT_SYNTAX);
        }
        String eventStartTime = data.substring(eventStartDelimiter + 5, eventEndDelimiter);
        String eventEndTime = data.substring(eventEndDelimiter + 3);

        tasks[currentItemNo++] = new Event(description, eventStartTime, eventEndTime);
        printFormattedReply("New haru.task.Event added:\n\t" + tasks[currentItemNo - 1].getFormattedTask());
    }

    private void incorrectCommandUsage(String commandTemplate) throws HaruException {
        throw new HaruException("Incorrect command usage.\n\tCorrect Usage: " + commandTemplate);
    }

    private void bye() {
        printFormattedReply("Bye! Have a wonderful day ahead :))");
        System.exit(0);
    }

    private void list() {
        String taskData = "";
        Task[] tasksCopy = Arrays.copyOf(tasks, currentItemNo);
        int counter = 0;
        for (Task data : tasksCopy) {
            String task = data.getFormattedTask();
            taskData += "\t" + ++counter + ". " + task + "\n";
        }
        printFormattedReply(tasksCopy.length == 0 ? "Your list is empty\n" : "Here is your list:\n" + taskData);
    }

    private void listMark(String args) {
        int index = validateIndex(args, "Invalid List Item");
        if (index == -1) {
            return;
        }
        tasks[index].markDone();
        String formattedString = tasks[index].getFormattedTask();
        printFormattedReply("\tharu.task.Task Marked as done:\n\t" + formattedString);
    }

    private void listUnmark(String args) {
        int index = validateIndex(args, "Invalid List Item");
        if (index == -1) {
            return;
        }
        tasks[index].unmarkDone();
        String formattedString = tasks[index].getFormattedTask();
        printFormattedReply("haru.task.Task Marked as not done:\n\t" + formattedString);
    }

    private int validateIndex(String args, String errorResponse) {
        int index;
        try {
            index = Integer.parseInt(args) - 1;
        } catch (NumberFormatException error) {
            printFormattedReply(errorResponse);
            return -1;
        }
        if (index >= 0 && index < currentItemNo) {
            return index;
        }
        printFormattedReply(errorResponse);
        return -1;
    }

    private void printFormattedReply(String reply) {
        System.out.println("\t" + SEPARATOR_LINE);
        System.out.println(getFormattedReply(reply));
        System.out.println("\t" + SEPARATOR_LINE);
    }

    private String getFormattedReply(String reply) {
        String formattedReply = "";
        for (String line : reply.trim().split("\n")) {
            formattedReply += "\t" + line + "\n";
        }
        return "\t" + formattedReply.trim();
    }


    public static void main(String[] args) {
        Haru chatbot = new Haru();
    }
}
