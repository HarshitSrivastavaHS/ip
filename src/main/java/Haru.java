import java.util.Arrays;
import java.util.Scanner;

public class Haru {

    private Task[] tasks;
    private int currentItemNo;

    Haru() {
        String logo = " \t_____                                                                        _____ \n" +
                "\t( ___ )                                                                      ( ___ )\n" +
                "\t |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   | \n" +
                "\t |   | __/\\\\\\________/\\\\\\____________________________________________________ |   | \n" +
                "\t |   | __\\/\\\\\\_______\\/\\\\\\___________________________________________________ |   | \n" +
                "\t |   | ___\\/\\\\\\_______\\/\\\\\\__________________________________________________ |   | \n" +
                "\t |   | ____\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\__/\\\\\\\\\\\\\\\\\\_____/\\\\/\\\\\\\\\\\\\\___/\\\\\\____/\\\\\\______ |   | \n" +
                "\t |   | _____\\/\\\\\\/////////\\\\\\_\\////////\\\\\\___\\/\\\\\\/////\\\\\\_\\/\\\\\\___\\/\\\\\\_____ |   | \n" +
                "\t |   | ______\\/\\\\\\_______\\/\\\\\\___/\\\\\\\\\\\\\\\\\\\\__\\/\\\\\\___\\///__\\/\\\\\\___\\/\\\\\\____ |   | \n" +
                "\t |   | _______\\/\\\\\\_______\\/\\\\\\__/\\\\\\/////\\\\\\__\\/\\\\\\_________\\/\\\\\\___\\/\\\\\\___ |   | \n" +
                "\t |   | ________\\/\\\\\\_______\\/\\\\\\_\\//\\\\\\\\\\\\\\\\/\\\\_\\/\\\\\\_________\\//\\\\\\\\\\\\\\\\\\___ |   | \n" +
                "\t |   | _________\\///________\\///___\\////////\\//__\\///___________\\/////////___ |   | \n" +
                "\t |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___| \n" +
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
        while (true) {
            System.out.print("> ");
            String commandLine = sc.nextLine().trim();
            String command = commandLine.substring(0, commandLine.contains(" ") ?commandLine.indexOf(" "):commandLine.length());
            String args = commandLine.equals(command)?"":commandLine.substring(commandLine.indexOf(" ")+1);
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
            default:
                listAdd(commandLine);
            }
        }
    }

    private void bye() {
        System.out.println("\t____________________________________________________________________________________\n" +
                "\tBye! Have a wonderful day ahead :))\n" +
                "\t____________________________________________________________________________________\n");
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
        System.out.println("\t____________________________________________________________________________________\n" +
                (tasksCopy.length == 0 ? "\t<EMPTY>\n" : taskData) +
                "\t____________________________________________________________________________________\n");
    }

    private void listAdd(String data) {
        tasks[currentItemNo++] = new Task(data, false);
        System.out.println("\t____________________________________________________________________________________");
        System.out.println("\tAdded: " + data);
        System.out.println("\t____________________________________________________________________________________");
    }

    private void listMark(String args) {
        int index = Integer.parseInt(args) - 1;
        tasks[index].markDone();
        String formattedString = tasks[index].getFormattedTask();
        System.out.println("\t____________________________________________________________________________________");
        System.out.println("\tTask Marked as done: ");
        System.out.println("\t\t"+formattedString);
        System.out.println("\t____________________________________________________________________________________");
    }

    private void listUnmark(String args) {
        int index = Integer.parseInt(args) - 1;
        tasks[index].unmarkDone();
        String formattedString = tasks[index].getFormattedTask();
        System.out.println("\t____________________________________________________________________________________");
        System.out.println("\tTask Marked as not done: ");
        System.out.println("\t\t"+formattedString);
        System.out.println("\t____________________________________________________________________________________");
    }

    public static void main(String[] args) {

        Haru chatbot = new Haru();

    }
}
