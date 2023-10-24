package com.pandey.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class HuffTreeTest {
    @Test
    void testBuildTree() {
        HashMap<Character, Integer> charFreq = new HashMap<>();
        charFreq.put('C', 32);
        charFreq.put('D', 42);
        charFreq.put('E', 120);
        charFreq.put('K', 7);
        charFreq.put('L', 42);
        charFreq.put('M', 24);
        charFreq.put('U', 37);
        charFreq.put('Z', 2);
        HuffTree actualHuffTree = HuffTree.buildTree(charFreq);
        Assertions.assertEquals(actualHuffTree.getWeight(), 306);
        Assertions.assertEquals(actualHuffTree.getLeftNode().getWeight(), 120);
    }
    @Test
    void testGetPrefixCodes() {
        HashMap<Character, Integer> charFreq = new HashMap<>();
        charFreq.put('C', 32);
        charFreq.put('D', 42);
        charFreq.put('E', 120);
        charFreq.put('K', 7);
        charFreq.put('L', 42);
        charFreq.put('M', 24);
        charFreq.put('U', 37);
        charFreq.put('Z', 2);
        HuffTree actualHuffTree = HuffTree.buildTree(charFreq);
        HashMap<Character, List<Byte>> actualPrefixCodes = HuffTree.getPrefixCodes(actualHuffTree);
        Map<Character, List<Byte>> expectedPrefixCodes = new HashMap<>();
        expectedPrefixCodes.put('C', Arrays.asList((byte) 1, (byte) 1, (byte) 1, (byte) 0));
        expectedPrefixCodes.put('D', Arrays.asList((byte) 1, (byte) 1, (byte) 0));
        expectedPrefixCodes.put('E', Arrays.asList((byte) 0));
        expectedPrefixCodes.put('U', Arrays.asList((byte) 1, (byte) 0, (byte) 0));
        expectedPrefixCodes.put('Z', Arrays.asList((byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 0, (byte) 0));
        expectedPrefixCodes.put('K', Arrays.asList((byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 0, (byte) 1));
        expectedPrefixCodes.put('L', Arrays.asList((byte) 1, (byte) 0, (byte) 1));
        expectedPrefixCodes.put('M', Arrays.asList((byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1));
        Assertions.assertEquals(expectedPrefixCodes, actualPrefixCodes);
    }
}