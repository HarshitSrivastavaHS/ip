package haru.application;

import haru.Haru;
import haru.command.commands.*;
import haru.datasave.SaveLoadManager;
import haru.exception.HaruException;
import haru.task.Task;
import haru.ui.Ui;
import haru.parser.Parser;

import haru.command.Command;
import haru.util.Counter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Main controller class responsible for initializing components,
 * loading saved data, and running the chatbot event loop.
 *
 * This class coordinates input parsing, command execution,
 * and saving of task data after every modification.
 */
public class Application {

    private final Scanner sc;
    private final Parser parser;

    private Map<String, Command> commands;

    private final ArrayList<Task> tasks;
    private final Counter currentItemNo;;

    private final SaveLoadManager dataManager;

    /**
     * Constructs the application, initializes internal components and loads saved data.
     */
    public Application() {
        currentItemNo = new Counter(0);
        tasks = new ArrayList<>();
        sc = new Scanner(System.in);
        parser = new Parser(null, currentItemNo);
        initialiseCommands();
        parser.setCommands(commands);
        dataManager = new SaveLoadManager();
        currentItemNo.value = dataManager.loadData(tasks);
    }

    /**
     * Starts the chatbot by greeting the user and beginning the input loop.
     */
    public void run() {
        Ui.greet();
        startChatbot();
    }

    /**
     * Continuously reads user input, processes commands, and saves data after each action.
     */
    private void startChatbot() {
        Ui.printPrompt();
        while (sc.hasNextLine()) {
            try {
                Command cmd = awaitUserInput();
                cmd.exec("");
                dataManager.saveData(tasks.toArray(Task[]::new));
            } catch (HaruException e) {
                Ui.printFormattedReply("Error: " + e.getMessage());
            }
            Ui.printPrompt();
        }
    }

    /**
     * Waits for user input, parses it, and returns the corresponding command.
     *
     * @return parsed command ready for execution
     * @throws HaruException if the input command is invalid
     */
    private Command awaitUserInput() throws HaruException {
        String input = sc.nextLine();
        return parser.parse(input);
    }

    /**
     * Registers all supported commands in a map for quick access.
     */
    private void initialiseCommands() {
        commands = Map.ofEntries(
                Map.entry("list", new List(tasks)),
                Map.entry("bye", new Bye()),
                Map.entry("todo", new AddTodo(tasks, currentItemNo)),
                Map.entry("deadline", new AddDeadline(tasks, currentItemNo)),
                Map.entry("event", new AddEvent(tasks, currentItemNo)),
                Map.entry("mark", new Mark(parser, tasks)),
                Map.entry("unmark", new Unmark(parser, tasks)),
                Map.entry("delete", new Delete(parser, tasks, currentItemNo)),
                Map.entry("find", new Find(tasks)),
                Map.entry("finddate", new FindDate(tasks))
        );
    }
}
