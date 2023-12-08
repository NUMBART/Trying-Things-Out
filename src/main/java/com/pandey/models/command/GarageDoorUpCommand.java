package com.pandey.models.command;

import com.pandey.models.GarageDoor;

public class GarageDoorUpCommand implements Command {
    GarageDoor garageDoor;
    public GarageDoorUpCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }
    @Override
    public void execute() {
        garageDoor.up();
    }
    public void undo() {
        garageDoor.down();
    }
}
