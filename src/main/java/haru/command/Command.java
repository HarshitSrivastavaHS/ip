package haru.command;

import haru.exception.HaruException;

/**
 * Represents a command that can be executed with optional arguments.
 *
 * All commands in the application implements this interface
 * to define their execution behavior.
 */
public interface Command {

    /**
     * Executes the command with the given arguments.
     *
     * @param args the arguments passed to the command, may be empty
     * @throws HaruException if an error occurs during command execution
     */
    void exec(String args) throws HaruException;
}
