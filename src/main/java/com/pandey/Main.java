package com.pandey;

import com.pandey.ducks.*;

public class Main {
    public static void main(String[] args) {
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