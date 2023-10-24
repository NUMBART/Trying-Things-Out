package com.pandey.models;

import lombok.Getter;

@Getter
public class HuffLeafNode extends HuffBaseNode {
    Character character;
    public HuffLeafNode(Character character, Integer weight) {
        super(weight);
        this.character = character;
    }
    @Override
    public boolean isLeaf() {
        return true;
    }
}
