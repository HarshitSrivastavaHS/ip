package haru.command.commands;

import haru.command.Command;
import haru.exception.HaruException;
import haru.ui.Ui;

public class Bye implements Command {

    @Override
    public void exec(String args) throws HaruException {
        Ui.printFormattedReply("Bye! Have a wonderful day ahead :))");
        System.exit(0);
    }
}
