package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.ui.Ui;

/**
 * Command that terminates the application.
 *
 * When executed, this command prints a farewell message to the user
 * and then exits the program immediately.
 */
public class Bye implements Command {

    /**
     * Executes the bye command.
     *
     * This method displays a goodbye message via {@link Ui}
     * and then terminates the application using {@code System.exit(0)}.
     *
     * @param args not used for this command
     * @throws HaruException never thrown in this implementation
     */
    @Override
    public void exec(String args) throws HaruException {
        Ui.printFormattedReply("Bye! Have a wonderful day ahead :))");
        System.exit(0);
    }
}
