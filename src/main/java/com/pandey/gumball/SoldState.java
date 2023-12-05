package com.pandey.gumball;

class SoldState extends State {
    @Override
    public void dispense(GumballMachine gumballMachine) {
        gumballMachine.releaseBall();
        if(gumballMachine.getCount() > 0)
            gumballMachine.setState(GumballMachineStates.NoQuarterState);
        else {
            System.out.println("Oops! out of gumballs");
            gumballMachine.setState(GumballMachineStates.SoldOutState);
        }
    }
}
