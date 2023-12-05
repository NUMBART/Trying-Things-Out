package com.pandey.gumball;

import com.pandey.exceptions.CrankTurnNotAllowedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GumballMachineTest {
    @Test
    public void happyFlowTest() throws CrankTurnNotAllowedException {
        GumballMachine gumballMachine = new GumballMachine(5);
        System.out.println(gumballMachine);
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        assertTrue(gumballMachine.getCount() == 3 || gumballMachine.getCount() == 4);
        assertEquals(gumballMachine.getState(), GumballMachineStates.NoQuarterState);
    }

    @Test
    public void noGumballAfterQuarterEjectTest() throws CrankTurnNotAllowedException {
        GumballMachine gumballMachine = new GumballMachine(5);
        System.out.println(gumballMachine);
        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter();
        gumballMachine.turnCrank();
        assertEquals(gumballMachine.getCount(), 5);
    }

    @Test
    public void noQuarterEjectTest() throws CrankTurnNotAllowedException {
        GumballMachine gumballMachine = new GumballMachine(5);
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        assertEquals(gumballMachine.getState(), GumballMachineStates.NoQuarterState);
        gumballMachine.ejectQuarter();
        System.out.println(gumballMachine);
    }

    @Test
    public void twoQuarterInsertedTest() throws CrankTurnNotAllowedException {
        GumballMachine gumballMachine = new GumballMachine(5);
        gumballMachine.insertQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        assertEquals(gumballMachine.getCount(), 0);
        assertEquals(gumballMachine.getState(), GumballMachineStates.SoldOutState);
    }

    @Test
    public void winnerStateTest() throws CrankTurnNotAllowedException {
        GumballMachine gumballMachine = new GumballMachine(20);
        int t = 15;
        while(t-- > 0) {
            gumballMachine.insertQuarter();
            gumballMachine.turnCrank();
        }
        // test not completed
    }
}