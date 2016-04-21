package pl.edu.agh.marcskow.ftpserver.client;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Client {
    private boolean isLoggedIn = false;
    private String username;
    private String password;
}
