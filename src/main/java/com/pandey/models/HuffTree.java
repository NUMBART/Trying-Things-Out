package com.pandey.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

@Getter
public class HuffTree implements Comparable<HuffTree> {
    HuffBaseNode root;
    HuffTree(Character character, Integer weight) {
        this.root = new HuffLeafNode(character, weight);
    }
    HuffTree(HuffBaseNode leftNode, HuffBaseNode rightNode) {
        this.root = new HuffInternalNode(leftNode, rightNode);
    }
    Integer getWeight() {
        return root.getWeight();
    }
    HuffBaseNode getLeftNode() {
        if(root.isLeaf()) return null;
        return ((HuffInternalNode) root).getLeftNode();
    }
    HuffBaseNode getRightNode() {
        if(root.isLeaf()) return null;
        return ((HuffInternalNode) root).getRightNode();
    }
    @Override
    public int compareTo(HuffTree huffTree) {
        return root.getWeight().compareTo(huffTree.getWeight());
    }
    public static HuffTree buildTree(HashMap<Character, Integer> charFreq) {
        PriorityQueue<HuffTree> minHuffTree = new PriorityQueue<>();
        charFreq.forEach((key, value) -> {
            minHuffTree.add(new HuffTree(key, value));
        });
        while(minHuffTree.size() > 1) {
            HuffTree leftHuffTree = minHuffTree.poll();
            HuffTree rightHuffTree = minHuffTree.poll();
            minHuffTree.add(new HuffTree(leftHuffTree.getRoot(), rightHuffTree.getRoot()));
        }
        return minHuffTree.poll();
    }
    public static HashMap<Character, List<Byte>> getPrefixCodes(HuffTree huffTree) {
        HashMap<Character, List<Byte>> prefixCodes = new HashMap<>();
        List<Byte> curPrefixCode = new ArrayList<>();
        HuffTree.traverseTree(huffTree.getRoot(), prefixCodes, curPrefixCode);
        return prefixCodes;
    }
    private static void traverseTree(HuffBaseNode root, HashMap<Character, List<Byte>> prefixCodes, List<Byte> curPrefixCode) {
        if(root.isLeaf()) {
            Character ch = ((HuffLeafNode)root).getCharacter();
            prefixCodes.put(ch, new ArrayList<>(curPrefixCode));
            return;
        }
        HuffBaseNode leftNode = ((HuffInternalNode)root).getLeftNode();
        HuffBaseNode rightNode = ((HuffInternalNode)root).getRightNode();
        curPrefixCode.add((byte) 0);
        traverseTree(leftNode, prefixCodes, curPrefixCode);
        curPrefixCode.set(curPrefixCode.size()-1, (byte) 1);
        traverseTree(rightNode, prefixCodes, curPrefixCode);
        curPrefixCode.remove(curPrefixCode.size()-1);
    }
}