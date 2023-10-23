package com.pandey.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class FileCompressor {
    public static HashMap<Character, Integer> scanCharacterCount(File inputFile) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(inputFile);
        HashMap<Character, Integer> charFreq = new HashMap<>();
        while(fileScanner.hasNextLine()) {
            String curLine = fileScanner.nextLine();
            for(int i = 0; i < curLine.length(); ++i)
                charFreq.put(curLine.charAt(i), charFreq.getOrDefault(curLine.charAt(i), 0) + 1);
        }
        fileScanner.close();
        return charFreq;
    }
    public static void compress(File inputFile) {
        try {
            HashMap<Character, Integer> charFreq = scanCharacterCount(inputFile);

        } catch (FileNotFoundException ex) {
            System.out.println("File " + inputFile.getName() + " not found");
        }
    }
}
