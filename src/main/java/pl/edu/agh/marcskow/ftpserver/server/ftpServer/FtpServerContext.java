package pl.edu.agh.marcskow.ftpserver.server.ftpServer;

/**
 * Basic server settings.
 */
public interface FtpServerContext {
    /**
     * @return path to the server root folder
     */
    String getRoot();

    /**
     * Set server root folder
     * @param path to the folder
     */
    void setRoot(String path);

    /**
     * @return amount of possible clients connected in the same time
     */
    int getThreadPool();

    /**
     * Set the amount of possible clients connected in the same time
     * @param threadPool amount of threads which can be made by executor service
     */
    void setThreadPool(int threadPool);

    /**
     * @return server port
     */
    int getPort();

    /**
     * Set port on which is server listening for clients
     * @param port port on which is server listening
     */
    void setPort(int port);
}
