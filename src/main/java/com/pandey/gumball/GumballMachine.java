package com.pandey.gumball;

import com.pandey.exceptions.CrankTurnNotAllowedException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GumballMachine {
    State state;
    int count;
    public GumballMachine(int count) {
        this.count = count;
        state = count == 0 ? GumballMachineStates.SoldOutState : GumballMachineStates.NoQuarterState;
    }
    public void insertQuarter() {
        state.insertQuarter(this);
    }
    public void ejectQuarter() {
        state.ejectQuarter(this);
    }
    public void turnCrank() throws CrankTurnNotAllowedException {
        try {
            state.turnCrank(this);
            state.dispense(this);
        } catch(CrankTurnNotAllowedException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void refill(int count) {
        this.count += count;
        System.out.println("Machine refilled");
        state.refill(this);
    }
    public void releaseBall() {
        count--;
        System.out.println("Gumball released");
    }

    @Override
    public String toString() {
        return "GumballMachine{" +
                "state=" + state.getClass() +
                ", count=" + count +
                '}';
    }
}
