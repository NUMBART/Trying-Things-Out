package com.pandey.ducks;

public class MallardDuck implements Duck {
    @Override
    public void fly() {
        System.out.println("Quack");
    }

    @Override
    public void quack() {
        System.out.println("I'm flying!");
    }
}
