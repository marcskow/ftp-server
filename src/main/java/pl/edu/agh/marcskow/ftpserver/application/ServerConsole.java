package pl.edu.agh.marcskow.ftpserver.application;

import pl.edu.agh.marcskow.ftpserver.server.implementation.Server;
import pl.edu.agh.marcskow.ftpserver.server.implementation.FtpServerContextImpl;

/**
 * Server version that can be integrated directly in the console
 */
public class ServerConsole {
    public static void main(String[] args) {
        Server server = new Server(new FtpServerContextImpl());
        Thread thread = new Thread(server);
        thread.start();
    }

}
