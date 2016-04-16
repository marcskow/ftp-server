package pl.edu.agh.marcskow.jpa.server.ftpServer;


public interface FtpServerContext {
    String getRoot();
    int getThreadPool();
    int getPort();
}
