package oop.hw3.ioSystem;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileParser {

    Path currentPath;
    int level = 1;

    public FileParser() {
        currentPath = Paths.get(System.getProperty("user.dir"));
        currentPath = Paths.get(currentPath.toString(), "levels_dir");
    }
    public char[][] readLevel() {
        String currentLevel = "level" + level + ".txt";
        level++;
        Path levelPath = Paths.get(currentPath.toString(), currentLevel);
        FileInput fileInput = new FileInput(levelPath.toString());
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
            level--;
        }
        return null;
    }

}
