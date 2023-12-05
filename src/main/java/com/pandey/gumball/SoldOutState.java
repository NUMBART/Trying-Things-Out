package com.pandey.gumball;

class SoldOutState extends State {
    @Override
    public void refill(GumballMachine gumballMachine) {
        gumballMachine.setState(GumballMachineStates.NoQuarterState);
    }
}
