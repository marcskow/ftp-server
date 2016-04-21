package pl.edu.agh.marcskow.ftpserver.clientHandler;

import java.io.IOException;

/**
 * Created by intenso on 06.04.16.
 */
public interface Session {
    void startSession() throws IOException;

    void handleRequestIfReceived() throws IOException;

    void write(String message) throws IOException;

    String read() throws IOException;

    boolean isLoggedIn();

    boolean isUp();

    void listenForActivity();

    void closeConnection() throws IOException;

    void setUserLogin(String login);
    String getUserLogin();

    void setUserPassword(String password);
    String getUserPassword();

    void setNeededCommand(String command);
    String getNeededCommand();

    void setIsLoggedIn(boolean isLoggedIn);
}
