package com.pandey.models.command;

import com.pandey.models.CeilingFan;

public class CeilingFanOffCommand implements Command {
    CeilingFan fan;
    public CeilingFanOffCommand(CeilingFan fan) {
        this.fan = fan;
    }
    @Override
    public void undo() {
        fan.high();
    }
    public void execute() {
        fan.off();
    }
}
