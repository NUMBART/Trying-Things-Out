package com.pandey.ducks;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TurkeyAdapter implements Duck {
    Turkey turkey;
    @Override
    public void fly() {
        for(int i = 0; i < 5; ++i)
            turkey.fly();
    }

    @Override
    public void quack() {
        turkey.gobble();
    }
}
