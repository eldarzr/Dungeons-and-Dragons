package com.company;


public class Interceptor implements OutputWriter {

    @Override
    public void writeOutput(String output) {
        System.out.println(output);
    }
}
