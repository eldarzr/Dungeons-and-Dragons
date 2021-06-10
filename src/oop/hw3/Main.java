package oop.hw3;

import oop.hw3.ioSystem.FileInput;
import oop.hw3.ioSystem.UserOutput;

import java.util.*;
public class Main {


    public static void main(String[] args) {
        // write your code here
        UserOutput userOutput = new UserOutput();
        try {
            GameManager gm = new GameManager(args[0]);
            while (true)
                gm.play();
        }
        catch (Exception e){
            userOutput.writeOutput("no levels path has been sent!");
        }

    }


}
