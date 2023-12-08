package com.pandey;

import com.pandey.ducks.*;
import com.pandey.hometheater.*;

public class Main {
    public static void main(String[] args) {
        adapterTestDrive();
        facadeTestDrive();
    }
    private static void facadeTestDrive() {
        System.out.println();
        System.out.println("Facade Pattern Test");
        System.out.println();
        Amplifier amp = new Amplifier("Amplifier");
        Tuner tuner = new Tuner("AM/FM Tuner", amp);
        StreamingPlayer player = new StreamingPlayer("Streaming Player", amp);
        CdPlayer cd = new CdPlayer("CD Player", amp);
        Projector projector = new Projector("Projector", player);
        TheaterLights lights = new TheaterLights("Theater Ceiling Lights");
        Screen screen = new Screen("Theater Screen");
        PopcornPopper popper = new PopcornPopper("Popcorn Popper");

        HomeTheaterFacade homeTheater =
                new HomeTheaterFacade(amp, cd, popper, projector, screen, player, lights, tuner);

        homeTheater.watchMovie("Raiders of the Lost Ark");
        homeTheater.endMovie();
    }
    private static void adapterTestDrive() {
        Duck duck = new MallardDuck();
        Turkey turkey = new WildTurkey();

        System.out.println("Mallard Duck's turn!");
        duck.quack();
        duck.fly();

        System.out.println("Wild Turkey's turn!");
        turkey.gobble();
        turkey.fly();

        System.out.println("Turkey adapted to Duck's turn!");
        Duck turkeyAdapter = new TurkeyAdapter(turkey);
        turkeyAdapter.quack();
        turkeyAdapter.fly();

        System.out.println("Duck adapted to Turkey's turn!");
        Turkey duckAdapter = new DuckAdapter(duck);
        duckAdapter.gobble();
        duckAdapter.fly();
    }
}