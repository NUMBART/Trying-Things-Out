package com.pandey.models.command;

import com.pandey.models.Light;

public class LightOffCommand implements Command {
    Light light;
    public LightOffCommand(Light light) {
        this.light = light;
    }
    @Override
    public void undo() {
        light.on();
    }
    public void execute() {
        light.off();
    }
}
