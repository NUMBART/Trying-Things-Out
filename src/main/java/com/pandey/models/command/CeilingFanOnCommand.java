package com.pandey.models.command;

import com.pandey.models.CeilingFan;

public class CeilingFanOnCommand implements Command {
    CeilingFan fan;
    public CeilingFanOnCommand(CeilingFan fan) {
        this.fan = fan;
    }
    @Override
    public void execute() {
        fan.high();
    }
    public void undo() {
        fan.off();
    }
}
