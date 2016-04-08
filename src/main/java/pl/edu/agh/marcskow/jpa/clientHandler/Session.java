package pl.edu.agh.marcskow.jpa.clientHandler;

import java.io.IOException;

/**
 * Created by intenso on 06.04.16.
 */
public interface Session {
    void startSession();

    void handleRequestIfReceived() throws IOException;

    void write(String message) throws IOException;

    String read() throws IOException;

    boolean isLoggedIn();

    boolean isUp();

    void closeConnection();
}
