package pl.edu.agh.marcskow.ftpserver.server.ftpServer;


public interface FtpServer {
    void start();
    void stop();
    boolean isRunning();
}
