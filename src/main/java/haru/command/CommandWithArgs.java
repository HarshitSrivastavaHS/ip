package haru.command;

import haru.exception.HaruException;

/**
 * Wraps a {@link Command} together with its arguments.
 *
 * This allows commands and their arguments to be bundled and executed later.
 */
public class CommandWithArgs implements Command{
    private final Command command;
    private final String args;

    /**
     * Creates a new {@code CommandWithArgs} instance.
     *
     * @param command the command to wrap
     * @param args the arguments to pass when executing the command
     */
    public CommandWithArgs(Command command, String args) {
        this.command = command;
        this.args = args;
    }

    /**
     * Executes the wrapped command with the stored arguments.
     *
     * The {@code notRequired} parameter is ignored.
     *
     * @param notRequired ignored; only present to satisfy the {@link Command} interface
     * @throws HaruException if the wrapped command throws an exception during execution
     */
    @Override
    public void exec(String notRequired) throws HaruException {
        this.command.exec(args);
    }
}
