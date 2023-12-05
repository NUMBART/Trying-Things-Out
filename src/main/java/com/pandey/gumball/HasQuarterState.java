package com.pandey.gumball;

import java.util.Random;

class HasQuarterState extends State {
    Random randomWinner = new Random(System.currentTimeMillis());
    @Override
    public void ejectQuarter(GumballMachine gumballMachine) {
        System.out.println("Quarter ejected");
        gumballMachine.setState(GumballMachineStates.NoQuarterState);
    }

    @Override
    public void turnCrank(GumballMachine gumballMachine) {
        int randomNum = randomWinner.nextInt(10);
        System.out.println("Crank turned");
        if(randomNum%10 == 0 && gumballMachine.getCount() > 1)
            gumballMachine.setState(GumballMachineStates.WinnerState);
        else
            gumballMachine.setState(GumballMachineStates.SoldState);
    }
}
