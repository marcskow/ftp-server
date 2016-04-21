package pl.edu.agh.marcskow.ftpserver.server.implementation;

import lombok.Getter;
import lombok.Setter;
import pl.edu.agh.marcskow.ftpserver.server.ftpServer.FtpServerContext;

@Getter @Setter
public class FtpServerContextImpl implements FtpServerContext{
    // TODO: 16.03.16
    private final String root = "ROOT";
    private final int port = 4444;
    private final int threadPool = 20;
}
