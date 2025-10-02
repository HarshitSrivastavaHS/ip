package haru.parser;

import haru.command.CommandWithArgs;
import haru.exception.HaruException;
import haru.task.Task;
import haru.command.Command;
import haru.ui.Ui;
import haru.util.Counter;

import java.util.Scanner;
import java.util.Map;

/**
 * Parses user input strings and converts them into executable {@link CommandWithArgs} objects.
 * Also provides validation for indices used in commands.
 */
public class Parser {

    private Map<String, Command> commands;
    private final Counter currentItemNo;

    /**
     * Constructs a Parser with a map of commands and a counter for the current number of items.
     *
     * @param commands Map of command names to {@link Command} objects
     * @param currentItemNo Counter representing the current number of items
     */
    public Parser(Map<String, Command> commands, Counter currentItemNo) {
        this.commands = commands;
        this.currentItemNo = currentItemNo;
    }

    /**
     * Updates the map of commands.
     *
     * @param commands New map of command names to {@link Command} objects
     */
    public void setCommands(Map<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * Parses a raw input string into a {@link CommandWithArgs}.
     * Replaces "<|>" with "<>" to avoid conflicts with saved formats.
     *
     * @param input User input string
     * @return {@link CommandWithArgs} representing the parsed command and its arguments
     * @throws HaruException If the input is empty or the command is invalid
     */
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

    /**
     * Validates a string argument as an integer index within the current item count.
     *
     * @param args String representing the index (index starts at 1 here)
     * @param errorResponse Error message to print if the index is invalid
     * @return Zero-based index if valid; -1 if invalid
     */
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
