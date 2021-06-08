package com.company;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class Main {

    public static void main(String[] args) throws URISyntaxException {
        // write your code here
        GameManager gm = new GameManager();
        gm.play();


//        UserInput userInput = new UserInput();
//
//
//
//        Path currentPath = Paths.get(System.getProperty("user.dir"));
//        currentPath = Paths.get(currentPath.toString(), "levels_dir", "level0.txt");
//        char[][] levels = readLevel(currentPath.toString());

/*
initialiter שמאתחל
        board שמנהל בורד
        fileParser לשלבים
         */
    }
    // LevelInit lv = new LevelInit();
    // lv.levelInitiallizer(levels);

//        gm.levelInitiallizer(levels);

//        while(true)
//
//    {
//        char c = userInput.readLine().charAt(0);
//        gm.onTick(c);
//
//    }



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
