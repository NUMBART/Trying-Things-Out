package com.pandey.services;

import com.pandey.models.HuffBaseNode;
import com.pandey.models.HuffInternalNode;
import com.pandey.models.HuffLeafNode;
import com.pandey.models.HuffTree;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FileCompressorService {
    public static HashMap<Character, Integer> scanCharacterCountOld(File inputFile) throws FileNotFoundException {
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

    public static HashMap<Character, Integer> scanCharacterCount(File inputFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        HashMap<Character, Integer> charFreq = new HashMap<>();
        int chInt;
        while((chInt = reader.read()) != -1) {
            Character ch = (char) chInt;
            charFreq.put(ch, charFreq.getOrDefault(ch, 0) + 1);
        }
        reader.close();
        return charFreq;
    }
    public static void compress(File inputFile, File compressedFile) throws IOException {
        try {
            HashMap<Character, Integer> charFreq = scanCharacterCount(inputFile);
            HuffTree completeHuffTree = HuffTree.buildTree(charFreq);
            var prefixCodes = HuffTree.getPrefixCodes(completeHuffTree);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            charFreq.forEach((key, val) -> {
                stringBuilder.append(key).append(" ").append(val).append("\n");
            });
            try(
                    FileOutputStream writer = new FileOutputStream(compressedFile);
                    BufferedReader reader = new BufferedReader(new FileReader(inputFile))
            ) {
                writer.write(String.valueOf(charFreq.size()).getBytes());
                writer.write(" ".getBytes());
                writer.write(String.valueOf(stringBuilder.toString().getBytes().length).getBytes());
                writer.write(stringBuilder.toString().getBytes());
                int charCode;
                byte curByte = 0; int pos = 0;
                List<Byte> compressedBytes = new ArrayList<>();
                List<Character> chars = new ArrayList<>();
                List<List<Byte>> prefCode = new ArrayList<>();
                while (((charCode = reader.read()) != -1)) {
                    char character = (char) charCode;
                    if(!prefixCodes.containsKey(character)) continue;
                    var prefixCode = prefixCodes.get(character);
                    chars.add(character);
                    prefCode.add(prefixCode);
                    for(int i = 0; i < prefixCode.size(); ++i) {
                        if(pos == 8) {
                            writer.write(curByte);
                            compressedBytes.add(curByte);
                            curByte = 0; pos = 0;
                        }
                        if(prefixCode.get(i) == 1)
                            curByte = (byte)(curByte | (1<<(7-pos)));
                        pos++;
                    }
                }
            } catch (IOException ex) {
                throw ex;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File " + inputFile.getName() + " not found");
        }
    }

    public static void decompress(File compressedFile, File decompressedFile) {
        try (
                FileInputStream fileInputStream = new FileInputStream(compressedFile);
                FileInputStream binaryInputStream = new FileInputStream(compressedFile);
                FileOutputStream fileOutputStream = new FileOutputStream(decompressedFile);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                BufferedWriter writer = new BufferedWriter(outputStreamWriter);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader)
        ) {
            int headerByteCount = 0;
            String headerSizeStr = reader.readLine();
            String[] headerSize = headerSizeStr.split(" ");
            int charCount = Integer.parseInt(headerSize[0]);
            headerByteCount = Integer.parseInt(headerSize[1]) + headerSizeStr.getBytes().length;
            HashMap<Character, Integer> charFreq = new HashMap<>();
            for (int i = 0; i < charCount; i++) {
                Character ch = (char)reader.read();
                reader.read();
                String intStr = reader.readLine();
                Integer freq = Integer.parseInt(intStr);
                charFreq.put(ch, freq);
            }
            binaryInputStream.skipNBytes(headerByteCount);
            HuffTree completeHuffTree = HuffTree.buildTree(charFreq);
            HuffBaseNode huffTreeRoot = completeHuffTree.getRoot();
            HuffBaseNode curNode = huffTreeRoot;
            while(binaryInputStream.available() != 0) {
                byte curByte = binaryInputStream.readNBytes(1)[0];
                for (int i = Byte.SIZE - 1; i >= 0; i--) {
                    int bit = (curByte >> i) & 1;
                    curNode = bit == 1 ? ((HuffInternalNode)curNode).getRightNode() : ((HuffInternalNode)curNode).getLeftNode();
                    if(curNode.isLeaf()) {
                        writer.write(((HuffLeafNode)curNode).getCharacter());
                        curNode = huffTreeRoot;
                    }
                }
            }
        } catch (IOException ex) {}
    }
}
