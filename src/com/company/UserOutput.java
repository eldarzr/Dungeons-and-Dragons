package com.company;


public class UserOutput implements OutputWriter {
  private static UserOutput userOP;

    public static UserOutput getInstance()
    {
        if(userOP==null)
            userOP=new UserOutput();
        return userOP;
    }

    @Override
    public void writeOutput(String output) {
        System.out.println(output);
    }
}
