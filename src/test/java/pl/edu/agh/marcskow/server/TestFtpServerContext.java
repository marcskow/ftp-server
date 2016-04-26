package pl.edu.agh.marcskow.server;

import lombok.Getter;
import lombok.Setter;
import pl.edu.agh.marcskow.ftpserver.server.ftpServer.FtpServerContext;

@Getter @Setter
public class TestFtpServerContext implements FtpServerContext {
    private String root = "6000";
    private int port = 6666;
    private int threadPool = 20;
}
