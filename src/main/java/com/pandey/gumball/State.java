package com.pandey.gumball;

import com.pandey.exceptions.CrankTurnNotAllowedException;

abstract class State {
    public void insertQuarter(GumballMachine gumballMachine) {
        System.out.println("Can't insert quarter!");
    }
    public void ejectQuarter(GumballMachine gumballMachine) {
        System.out.println("No quarter available to eject!");
    }
    public void turnCrank(GumballMachine gumballMachine) throws CrankTurnNotAllowedException {
        throw new CrankTurnNotAllowedException();
    }
    public void dispense(GumballMachine gumballMachine) {
        System.out.println("Can't dispense gumball right now!");
    }
    public void refill(GumballMachine gumballMachine) {
        System.out.println("Can't refill right now!");
    }
}
