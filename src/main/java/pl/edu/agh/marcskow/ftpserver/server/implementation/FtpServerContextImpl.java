package pl.edu.agh.marcskow.ftpserver.server.implementation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.agh.marcskow.ftpserver.server.ftpServer.FtpServerContext;

/**
 * FtpServerContext implementation
 * @see FtpServerContext
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class FtpServerContextImpl implements FtpServerContext{
    private int port = 4444;
    private int threadPool = 20;
    private String root = "/home/intenso/ftpServer";
}
