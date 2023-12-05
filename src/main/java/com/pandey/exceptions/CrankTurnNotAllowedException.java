package com.pandey.exceptions;

public class CrankTurnNotAllowedException extends Exception {
    public CrankTurnNotAllowedException() {
        super("Cannot turn the crank!");
    }
}
