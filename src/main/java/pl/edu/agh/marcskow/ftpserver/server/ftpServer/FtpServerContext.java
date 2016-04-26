package pl.edu.agh.marcskow.ftpserver.server.ftpServer;


public interface FtpServerContext {
    String getRoot();
    void setRoot(String path);

    int getThreadPool();
    void setThreadPool(int threadPool);

    int getPort();
    void setPort(int port);
}
