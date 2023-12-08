package com.pandey.models;

public class Light {
    private final String location;

    public Light(String location) {
        this.location = location;
    }
    public void on() {
        System.out.println(location + " light turned on.");
    }

    public void off() {
        System.out.println(location + " light turned off.");
    }
}
