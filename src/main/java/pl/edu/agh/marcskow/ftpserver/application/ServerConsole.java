package pl.edu.agh.marcskow.ftpserver.application;

import pl.edu.agh.marcskow.ftpserver.server.Server;
import pl.edu.agh.marcskow.ftpserver.server.implementation.FtpServerContextImpl;

/**
 * Created by intenso on 09.04.16.
 */

public class ServerConsole {
    public static void main(String[] args) {
        Server server = new Server(new FtpServerContextImpl());
        Thread thread = new Thread(server);
        thread.start();
    }

}
