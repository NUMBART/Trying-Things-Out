package com.pandey.gumball;

class NoQuarterState extends State {
    @Override
    public void insertQuarter(GumballMachine gumballMachine) {
        System.out.println("Quarter inserted");
        gumballMachine.setState(GumballMachineStates.HasQuarterState);
    }
}
