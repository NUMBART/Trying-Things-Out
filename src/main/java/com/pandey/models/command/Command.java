package com.pandey.models.command;

public interface Command {
    void execute();
    void undo();
}
