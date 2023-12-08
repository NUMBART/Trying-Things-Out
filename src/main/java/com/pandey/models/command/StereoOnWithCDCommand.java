package com.pandey.models.command;

import com.pandey.models.Stereo;

public class StereoOnWithCDCommand implements Command {
    Stereo stereo;
    public StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }
    public void execute() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);
    }
    public void undo() {
        stereo.off();
    }
}
