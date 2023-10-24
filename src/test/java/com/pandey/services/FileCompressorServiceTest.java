package com.pandey.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

class FileCompressorServiceTest {
    @Test
    void testScanCharacterCount() {
        try {
            HashMap<Character, Integer> charFreq = FileCompressorService.scanCharacterCount(new File("src/main/resources/LesMiserablesByVictorHugo.txt"));
            Assertions.assertEquals(333, charFreq.get('X'));
            Assertions.assertEquals(223000, charFreq.get('t'));
        } catch(IOException ex) {
            System.out.println("Add input file to run test");
        }
    }
}