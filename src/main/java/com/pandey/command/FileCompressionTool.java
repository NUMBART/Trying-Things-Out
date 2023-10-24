package com.pandey.command;

import com.pandey.services.FileCompressorService;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;

public class FileCompressionTool {
    public static void execute(String[] args) {
        Options options = getOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();
        try {
            // TODO: 24/10/23 handle error if not correct file name given
            CommandLine cmd = parser.parse(options, args);
            if(cmd.hasOption("c")) {
                File inputFile = new File(cmd.getOptionValue("c"));
                File compressedFile = new File(cmd.getOptionValue("o"));
                FileCompressorService.compress(inputFile, compressedFile);
            }
            else if(cmd.hasOption("dc")) {
                File compressedFile = new File(cmd.getOptionValue("dc"));
                File decompressedFile = new File(cmd.getOptionValue("o"));
                FileCompressorService.decompress(compressedFile, decompressedFile);
            }
            else if(cmd.hasOption("h")) {
                helpFormatter.printHelp("FileCompressionCLI", options);
            }
            else System.out.println("Invalid command. Use -h or --help for usage information.");
        } catch (ParseException | IOException ex) {
            System.err.println("Error: " + ex.getMessage() + "\n" + ex.getStackTrace());
        }
    }
    private static Options getOptions() {
        Options options = new Options();
        Option compressOption = Option.builder("c")
                .longOpt("compress")
                .hasArg()
                .argName("inputFilePath")
                .desc("Compress the input text file")
                .build();
        Option decompressOption = Option.builder("dc")
                .longOpt("decompress")
                .hasArg()
                .argName("compressedFilePath")
                .desc("Decompress the compressed file")
                .build();
        Option outputOption = Option.builder("o")
                .longOpt("output")
                .hasArg()
                .argName("outputFileName")
                .desc("Specify the output file name")
                .build();
        Option helpOption = Option.builder("h")
                .longOpt("help")
                .desc("Display description, usage, options, and examples")
                .build();
        options.addOption(compressOption);
        options.addOption(decompressOption);
        options.addOption(outputOption);
        options.addOption(helpOption);
        return options;
    }
}
