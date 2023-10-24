# File Compression CLI Tool

This CLI tool is designed for file compression using Huffman coding, a popular algorithm for lossless data compression. On the Gutenberg top 100 texts, this tool achieves an average file compression ratio of 1.78

## Usage
You can use a sample text file, such as 'LesMiserablesByVictorHugo.txt,' located in the resources folder along with commands below.

### Prerequisites

Before using the File Compression CLI tool, ensure that you have the following prerequisites:

- Java Development Kit (JDK) installed (version 20 or later)
- Apache Maven installed

### 1. Compile the Project

To compile the File Compression CLI Tool, open your terminal or command prompt and navigate to the project directory. Then run the following Maven command:

```sh
mvn compile -f pom.xml
```
This command compiles the source code and prepares the tool for use.

### 2. Compress a File

To compress a file, you can use the following Maven command. Replace <inputFilePath> with the path to the file you want to compress and <outputFileName> with the desired name of the compressed file:
```sh
mvn exec:java -Dexec.mainClass="com.pandey.Main" -Dexec.args="-c <inputFilePath> -o <outputFileName>"
```
Example:
```sh
mvn exec:java -Dexec.mainClass="com.pandey.Main" -Dexec.args="-c src/main/resources/LesMiserablesByVictorHugo.txt -o src/main/resources/LesMiserablesByVictorHugo[Compressed]"
```
This command will compress the specified file.

### 3. Decompress a File
To decompress a file, use the following Maven command. Replace <compressedFilePath> with the path to the compressed file and <outputFilePath> with the path where you want to save the decompressed file:

```sh
mvn exec:java -Dexec.mainClass="com.pandey.Main" -Dexec.args="-dc <compressedFilePath> -o <outputFilePath>"
```
Example:

```sh
mvn exec:java -Dexec.mainClass="com.pandey.Main" -Dexec.args="-dc src/main/resources/LesMiserablesByVictorHugo[Compressed] -o src/main/resources/LesMiserablesByVictorHugo[Decompressed]"
```
This command will decompress the specified file and save it to the output path.