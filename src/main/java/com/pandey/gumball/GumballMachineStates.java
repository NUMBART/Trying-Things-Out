package com.pandey.gumball;

class GumballMachineStates {
    public static final State NoQuarterState = new NoQuarterState();
    public static final State HasQuarterState = new HasQuarterState();
    public static final State SoldState = new SoldState();
    public static final State SoldOutState = new SoldOutState();
    public static final State WinnerState = new WinnerState();
}
