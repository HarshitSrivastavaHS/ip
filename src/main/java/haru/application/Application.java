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

public class Application {

    private final Scanner sc;
    private final Parser parser;

    private Map<String, Command> commands;

    private final ArrayList<Task> tasks;
    private final Counter currentItemNo;;

    private final SaveLoadManager dataManager;


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

    public void run() {
        Ui.greet();
        startChatbot();
    }

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

    private Command awaitUserInput() throws HaruException {
        String input = sc.nextLine();
        return parser.parse(input);
    }

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
                Map.entry("find", new Find(tasks))
        );
    }
}
