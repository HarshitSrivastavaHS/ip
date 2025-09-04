import java.util.Arrays;
import java.util.Scanner;

public class Haru {

    private Task[] tasks;
    private int currentItemNo;

    Haru() {
        String logo = " \t _____                                                                        _____\n" +
                "\t( ___ )                                                                      ( ___ )\n" +
                "\t |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   |\n" +
                "\t |   | __/\\\\\\________/\\\\\\____________________________________________________ |   |\n" +
                "\t |   | __\\/\\\\\\_______\\/\\\\\\___________________________________________________ |   |\n" +
                "\t |   | ___\\/\\\\\\_______\\/\\\\\\__________________________________________________ |   |\n" +
                "\t |   | ____\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\__/\\\\\\\\\\\\\\\\\\_____/\\\\/\\\\\\\\\\\\\\___/\\\\\\____/\\\\\\______ |   |\n" +
                "\t |   | _____\\/\\\\\\/////////\\\\\\_\\////////\\\\\\___\\/\\\\\\/////\\\\\\_\\/\\\\\\___\\/\\\\\\_____ |   |\n" +
                "\t |   | ______\\/\\\\\\_______\\/\\\\\\___/\\\\\\\\\\\\\\\\\\\\__\\/\\\\\\___\\///__\\/\\\\\\___\\/\\\\\\____ |   |\n" +
                "\t |   | _______\\/\\\\\\_______\\/\\\\\\__/\\\\\\/////\\\\\\__\\/\\\\\\_________\\/\\\\\\___\\/\\\\\\___ |   |\n" +
                "\t |   | ________\\/\\\\\\_______\\/\\\\\\_\\//\\\\\\\\\\\\\\\\/\\\\_\\/\\\\\\_________\\//\\\\\\\\\\\\\\\\\\___ |   |\n" +
                "\t |   | _________\\///________\\///___\\////////\\//__\\///___________\\/////////___ |   |\n" +
                "\t |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___|\n" +
                "\t(_____)                                                                      (_____)\n";
        System.out.println("\t____________________________________________________________________________________\n" +
                "\tHello I'm\n" +
                logo +
                "\n\tWhat can I do for you?\n" +
                "\t____________________________________________________________________________________\n");

        tasks = new Task[100];
        currentItemNo = 0;
        handleCommands();
    }

    public void handleCommands() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            System.out.print("> ");
            String commandLine = sc.nextLine().trim();
            String command = commandLine.substring(0, commandLine.contains(" ") ? commandLine.indexOf(" ") : commandLine.length());
            String args = commandLine.equals(command) ? "" : commandLine.substring(commandLine.indexOf(" ") + 1);
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
                listAdd(commandLine);
            }
        }
    }

    private void addTodo(String data) {
        tasks[currentItemNo++] = new Todo(data);
        printFormattedReply("New Todo added:\n\t"+tasks[currentItemNo-1].getFormattedTask());
    }

    private void addDeadline(String data) {
        int delimiter = data.indexOf("/by");
        if (delimiter == -1) {
            incorrectCommandUsage("deadline <description> /by <deadline>");
            return;
        }
        String description = data.substring(0, delimiter);
        String deadline = data.substring(delimiter+3);
        tasks[currentItemNo++] = new Deadline(description, deadline);
        printFormattedReply("New Deadline added:\n\t"+tasks[currentItemNo-1].getFormattedTask());
    }

    public void addEvent(String data) {
        int eventStartDelimiter = data.indexOf("/from");
        int eventEndDelimiter = data.indexOf("/to");
        if (eventStartDelimiter == -1 || eventEndDelimiter == -1) {
            incorrectCommandUsage("event <description> /from <startTime> /to <end time>");
            return;
        }
        String description = data.substring(0, eventStartDelimiter);
        String eventStartTime = data.substring(eventStartDelimiter+5, eventEndDelimiter);
        String eventEndTime = data.substring(eventEndDelimiter+3);

        tasks[currentItemNo++] = new Event(description, eventStartTime, eventEndTime);
        printFormattedReply("New Event added:\n\t" + tasks[currentItemNo-1].getFormattedTask());
    }

    public void incorrectCommandUsage(String commandTemplate) {
        printFormattedReply("Incorrect command usage.\n\tCorrect Usage: "+commandTemplate);
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
        printFormattedReply(tasksCopy.length == 0 ? "Your list is empty\n" : "Here is your list:\n"+taskData);
    }

    private void listAdd(String data) {
        tasks[currentItemNo++] = new Task(data);
        printFormattedReply("\tAdded: " + data);
    }

    private void listMark(String args) {
        int index = validateIndex(args, "Invalid List Item");
        if (index == -1) {
            return;
        }
        tasks[index].markDone();
        String formattedString = tasks[index].getFormattedTask();
        printFormattedReply("\tTask Marked as done:\n\t" + formattedString);
    }

    private void listUnmark(String args) {
        int index = validateIndex(args, "Invalid List Item");
        if (index == -1) {
            return;
        }
        tasks[index].unmarkDone();
        String formattedString = tasks[index].getFormattedTask();
        printFormattedReply("Task Marked as not done:\n\t" + formattedString);
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
        System.out.println("\t____________________________________________________________________________________");
        System.out.println(getFormattedReply(reply));
        System.out.println("\t____________________________________________________________________________________");
    }

    private String getFormattedReply(String reply) {
        String formattedReply = "";
        for (String line: reply.trim().split("\n")) {
            formattedReply += "\t" + line + "\n";
        }
        return "\t" + formattedReply.trim();
    }



    public static void main(String[] args) {

        Haru chatbot = new Haru();

    }
}
