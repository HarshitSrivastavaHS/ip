package haru.command;

import haru.command.Command;
import haru.exception.HaruException;

public class CommandWithArgs implements Command{
    private final Command command;
    private final String args;

    public CommandWithArgs(Command command, String args) {
        this.command = command;
        this.args = args;
    }

    @Override
    public void exec(String notRequired) throws HaruException {
        this.command.exec(args);
    }
}
