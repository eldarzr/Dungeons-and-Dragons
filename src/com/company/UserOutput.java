package com.company;


public class UserOutput implements OutputWriter {

    @Override
    public void writeOutput(String output) {
        System.out.println(output);
    }
}
