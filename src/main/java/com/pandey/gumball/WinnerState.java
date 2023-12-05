package com.pandey.gumball;

public class WinnerState extends State {
    @Override
    public void dispense(GumballMachine gumballMachine) {
        gumballMachine.releaseBall();
        System.out.println("Gumball released");
        if(gumballMachine.getCount() > 0) {
            System.out.println("Gumball release");
            System.out.println("You just won a free Gumball!");
            gumballMachine.releaseBall();
            if (gumballMachine.getCount() > 0) {
                gumballMachine.setState(GumballMachineStates.NoQuarterState);
            } else {
                System.out.println("Oops, out of gumballs!");
                gumballMachine.setState(GumballMachineStates.SoldOutState);
            }
        }
        else {
            System.out.println("Oops, out of gumballs!");
            gumballMachine.setState(GumballMachineStates.SoldOutState);
        }
    }
}
