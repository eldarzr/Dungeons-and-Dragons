package com.company;
import java.net.URISyntaxException;
import java.util.*;
public class Main {

    public static void main(String[] args) throws URISyntaxException {
	// write your code here
        UserOutput userOutput = UserOutput.getInstance();
        UserInput userInput = new UserInput();
        //char[][] levels = readLevel("C:\\Users\\אלדר זריהן\\Documents\\לימודים\\סמסטר ב'\\עקרונות תכנות מונחה עצמים\\dnd demo\\levels_dir\\level0.txt");
        char[][] levels = readLevel(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "levels_dir/level0.txt");
        for(char[] carr : levels){
            userOutput.writeOutput(Arrays.toString(carr));
        }
       // LevelInit lv = new LevelInit();
       // lv.levelInitiallizer(levels);

        GameManager gm = new GameManager();
        gm.levelInitiallizer(levels);

        while (true){
            char c = userInput.readLine().charAt(0);
            gm.onTick(c);

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
