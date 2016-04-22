package pl.edu.agh.marcskow.ftpserver.clientHandler;

import java.io.IOException;

/**
 * Created by intenso on 06.04.16.
 */
public interface Session {
    String DEFAULT_ROOT_DIRECTORY = "/home/intenso/ftpServer";

    void startSession() throws IOException;

    void handleRequestIfReceived() throws IOException;

    void write(String message) throws IOException;

    String read() throws IOException;

    boolean getIsLoggedIn();

    boolean isUp();

    void listenForActivity();

    void closeConnection() throws IOException;

    void setUserLogin(String login);
    String getUserLogin();

    void setPassiveServerSocket(PassiveServer passiveServer);
    PassiveServer getPassiveServerSocket();

    String getRootDirectory();
    void setRootDirectory(String rootDirectory);

    String getLastCommand();
    void setLastCommand(String lastCommand);

    void setIsLoggedIn(boolean isLoggedIn);
}
