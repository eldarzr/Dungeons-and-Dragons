package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FileInput implements InputReader{
    private List<String> lines;
    private int nextLine;

    @Override
    public String readLine() {
        return lines.get(nextLine++);
    }

    public List<String> readAllLines() {
        return lines;
    }

    public FileInput(String filePath){
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch(IOException e){
            System.err.println("Error while reading: " + filePath);
        }
        nextLine = 0;
    }

}
