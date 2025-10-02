package haru.command;

import haru.exception.HaruException;

public interface Command {
    void exec(String args) throws HaruException;
}
