package pl.edu.agh.marcskow.ftpserver.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Commands {
    private final static List<String> passiveCommands = new ArrayList<>(Arrays.asList("LIST", "STOR", "RETR", "APPE"));
    private final static List<String> withoutAuthorization = new ArrayList<>(Arrays.asList("USER", "PASS", "QUIT"));

    public static boolean isPassive(String title){
        return passiveCommands.contains(title);
    }

    public static boolean needAuthorization(String title){
       return !withoutAuthorization.contains(title);
    }
}
