package pl.edu.agh.marcskow.ftpserver.server.ftpServer;

/**
 * Starting point of the server. It invokes a new listener thread.
 */
public interface FtpServer {
    /**
     * Start the server.
     */
    void start();

    /**
     * Stop the server.
     */
    void stop();

    /**
     * Is server running
     * @return true if the server is running
     */
    boolean isRunning();
}
