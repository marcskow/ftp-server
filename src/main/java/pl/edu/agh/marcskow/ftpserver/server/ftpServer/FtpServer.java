package pl.edu.agh.marcskow.ftpserver.server.ftpServer;

/**
 * Created by intenso on 15.03.16.
 */
public interface FtpServer {
    void start();
    void stop();
    boolean isRunning();
}
