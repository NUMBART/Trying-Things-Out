package com.pandey.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class HuffBaseNode {
    Integer weight;
    public abstract boolean isLeaf();
}
