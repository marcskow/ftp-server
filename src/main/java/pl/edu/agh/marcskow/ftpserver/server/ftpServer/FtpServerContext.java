package pl.edu.agh.marcskow.ftpserver.server.ftpServer;


public interface FtpServerContext {
    String getRoot();
    int getThreadPool();
    int getPort();
}
