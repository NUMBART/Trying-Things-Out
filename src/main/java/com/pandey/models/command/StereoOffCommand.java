package com.pandey.models.command;

import com.pandey.models.Stereo;

public class StereoOffCommand implements Command {
    Stereo stereo;
    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }
    public void undo() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);
    }
    public void execute() {
        stereo.off();
    }
}
