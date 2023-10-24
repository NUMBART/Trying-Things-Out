package com.pandey;

import com.pandey.command.FileCompressionTool;

// src/main/resources/LesMiserablesByVictorHugo.txt
public class Main {
    public static void main(String[] args) {
        FileCompressionTool.execute(args);
//        String filepath = "src/main/resources/";
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter the file you want to compress: ");
//        String fileName = sc.nextLine();
//        System.out.println("Enter the name of the compressed file: ");
//        String compressedFileName = sc.nextLine();
//        System.out.println("Enter the name of the decompressed file: ");
//        String decompressedFileName = sc.nextLine();
//        File file = new File(filepath+fileName);
//        try {
// //         change the compressed file path from string to file type
//            FileCompressorService.compress(file, filepath + compressedFileName);
//            FileCompressorService.decompress(filepath + compressedFileName, filepath + decompressedFileName);
//        } catch (IOException e) {
//            System.out.println("Unable to create compressed file.");
//        }
//        sc.close();
    }
}