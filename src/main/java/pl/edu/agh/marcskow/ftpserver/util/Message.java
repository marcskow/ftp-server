package pl.edu.agh.marcskow.ftpserver.util;

import lombok.Data;

/**
 * Request that the server receives from the client.
 */
@Data
public class Message {
    private String title;
    private String[] args;
    private int length;

    /**
     * Request may have arguments, this function return argument of specified index
     * @param index which argument to return
     * @return specified argument
     */
    public String getArgument(int index){
        return args[index];
    }

    public String toString(){
        String result = title;
        for (String s : args) {
            result = result + " " + s;
        }
        return result;
    }
}
