package com.company;

import java.io.InputStream;
import java.util.Scanner;

public class UserInput implements InputReader{

    private Scanner sc;

    public UserInput(){
        sc = new Scanner(System.in);
    }

    @Override
    public String readLine() {
        return sc.nextLine();
    }
}
