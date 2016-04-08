package pl.edu.agh.marcskow.jpa.util;

import lombok.Data;

@Data
public class Message {
    private String title;
    private String[] args;
    private int length;

    public String getArgument(int index){
        return args[index];
    }
}
