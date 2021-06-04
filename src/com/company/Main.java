package com.company;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class Main {

    public static void main(String[] args) {
	// write your code here
        Interceptor interceptor = new Interceptor();
        UserInput userInput = new UserInput();
        char[][] levels = readLevel("C:\\Users\\אלדר זריהן\\Documents\\לימודים\\סמסטר ב'\\עקרונות תכנות מונחה עצמים\\dnd demo\\levels_dir\\level1.txt");
        for(char[] carr : levels){
            interceptor.writeOutput(Arrays.toString(carr));
        }
        while (true){
            userInput.readLine();
        }
    }

    public static char[][] readLevel(String path) {
        FileInput fileInput = new FileInput(path);
        List<String> lines = fileInput.readAllLines();
        char[][] levels;
        try {
            //lines = Files.readAllLines(Paths.get(path));
            levels = new char[lines.size()][];
            for (int i=0; i<lines.size(); i++){
                String s = lines.get(i);
                levels[i] = new char[s.length()];
                for(int j=0; j<s.length(); j++){
                    levels[i][j] = s.charAt(j);
                }

            }
            return levels;
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" +
                    e.getStackTrace());
        }
        return null;
    }
}
