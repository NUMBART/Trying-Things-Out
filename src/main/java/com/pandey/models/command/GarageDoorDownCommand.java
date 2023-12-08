package com.pandey.models.command;

import com.pandey.models.GarageDoor;

public class GarageDoorDownCommand implements Command {
    GarageDoor garageDoor;
    public GarageDoorDownCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }
    @Override
    public void undo() {
        garageDoor.up();
    }
    public void execute() {
        garageDoor.down();
    }
}
