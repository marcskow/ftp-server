package pl.edu.agh.marcskow.ftpserver.clientHandler;

import lombok.Getter;
import lombok.Setter;

/**
 * Simple data class which contains basic data of current logged in user
 */
@Getter @Setter
public class Client {
    private boolean isLoggedIn = false;
    private String username;
    private String password;
}
