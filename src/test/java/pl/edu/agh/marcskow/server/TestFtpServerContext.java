package pl.edu.agh.marcskow.server;

import lombok.Getter;
import pl.edu.agh.marcskow.jpa.server.ftpServer.FtpServerContext;

@Getter
public class TestFtpServerContext implements FtpServerContext {
    private String root;
    private int port;
    private int threadPool;

    public TestFtpServerContext(){
        this("ROOT",6666,20);
    }

    public TestFtpServerContext(String root, int port, int threadPool){
        this.root = root;
        this.port = port;
        this.threadPool = threadPool;
    }
}
