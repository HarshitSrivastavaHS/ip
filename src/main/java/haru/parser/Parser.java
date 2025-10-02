package haru.parser;

import haru.command.CommandWithArgs;
import haru.exception.HaruException;
import haru.task.Task;
import haru.command.Command;
import haru.ui.Ui;
import haru.util.Counter;

import java.util.Scanner;
import java.util.Map;

public class Parser {

    private Map<String, Command> commands;
    private final Counter currentItemNo;

    public Parser(Map<String, Command> commands, Counter currentItemNo) {
        this.commands = commands;
        this.currentItemNo = currentItemNo;
    }

    public void setCommands(Map<String, Command> commands) {
        this.commands = commands;
    }

    public Command parse(String input) throws HaruException{
        input = input.trim().replaceAll("<\\|>", "<>");;

        if (input.isEmpty()) {
            throw new HaruException("Empty Command");
        }

        String[] commandParts = input.split(" ", 2);
        String commandName = commandParts[0];
        String args = commandParts.length > 1 ? commandParts[1] : "";

        Command command = commands.get(commandName.toLowerCase());
        if (command == null) {
            throw new HaruException("Invalid Command. Not quite sure what you mean by \"" + input + "\" O_o");
        }

        return new CommandWithArgs(command, args);

    }

    public int validateIndex(String args, String errorResponse) {
        int index;
        try {
            index = Integer.parseInt(args) - 1;
        } catch (NumberFormatException error) {
            Ui.printFormattedReply(errorResponse);
            return -1;
        }
        if (index >= 0 && index < currentItemNo.value) {
            return index;
        }
        Ui.printFormattedReply(errorResponse);
        return -1;
    }
}
