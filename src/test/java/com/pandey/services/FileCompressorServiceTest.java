package com.pandey.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FileCompressorTest {
    @Test
    void testScanCharacterCount() {
        try {
            HashMap<Character, Integer> charFreq = FileCompressor.scanCharacterCount(new File("src/main/resources/LesMiserablesByVictorHugo.txt"));
            Assertions.assertEquals(333, charFreq.get('X'));
            Assertions.assertEquals(223000, charFreq.get('t'));
        } catch(FileNotFoundException ex) {
            System.out.println("Add input file to run test");
        }
    }
}