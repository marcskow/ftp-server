package pl.edu.agh.marcskow.jpa.server.ftpServer;

import pl.edu.agh.marcskow.jpa.server.ftpServer.FtpServer;

/**
 * Created by intenso on 16.03.16.
 */
public interface FtpServerFactory {
    FtpServer getServer();
}
