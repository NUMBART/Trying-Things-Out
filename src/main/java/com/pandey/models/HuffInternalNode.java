package com.pandey.models;

import lombok.Getter;

@Getter
public class HuffInternalNode extends HuffBaseNode {
    HuffBaseNode leftNode;
    HuffBaseNode rightNode;
    public HuffInternalNode(HuffBaseNode leftNode, HuffBaseNode rightNode) {
        super(leftNode.getWeight() + rightNode.getWeight());
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }
}
