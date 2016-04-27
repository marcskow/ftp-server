package pl.edu.agh.marcskow.ftpserver.clientHandler;

import java.io.IOException;


/**
 * Session is responsible for maintaining contact with the client, handling requests, sending responses.
 */
public interface Session {
    String DEFAULT_ROOT_DIRECTORY = "/home/intenso/ftpServer";

    /**
     * Start session.
     * @throws IOException
     */
    void startSession() throws IOException;

    /**
     * If request is received session invokes server response.
     * @throws IOException
     */
    void handleRequestIfReceived() throws IOException;

    /**
     * Write message to the output stream (to the client socket)
     * @param message message which client will receive
     * @throws IOException
     */
    void write(String message) throws IOException;

    /**
     * Read requests from clients
     * @return read request
     * @throws IOException
     */
    String read() throws IOException;

    /**
     * @return is session up
     */
    boolean isUp();

    /**
     * Start listening is client active.
     * If client is not active for specified time session is closing connection
     */
    void listenForActivity();

    /**
     * Stop listening for requests and close connection with client
     * @throws IOException
     */
    void closeConnection() throws IOException;

    /**
     * Save username of current user
     * @param login username
     */
    void setUserLogin(String login);

    /**
     * @return username of current user
     */
    String getUserLogin();

    /**
     * @param passiveServer server socket on which will server receive and send data
     */
    void setPassiveServerSocket(PassiveServer passiveServer);

    /**
     * @return server socket on which is server receiving and sending data
     */
    PassiveServer getPassiveServerSocket();

    /**
     * @return path of root folder
     */
    String getRootDirectory();

    /**
     * @param rootDirectory path of new root folder
     */
    void setRootDirectory(String rootDirectory);

    /**
     * @return last command received from client
     */
    String getLastCommand();

    /**
     * @return is current user logged in
     */
    boolean getIsLoggedIn();

    /**
     * Set is current user logged in
     * @param isLoggedIn is logged in
     */
    void setIsLoggedIn(boolean isLoggedIn);
}
