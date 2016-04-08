package pl.edu.agh.marcskow.jpa.client;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Client {
    private boolean isLoggedIn;
    private String username;
    private String password;
}
