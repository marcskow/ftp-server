package pl.edu.agh.marcskow.jpa.application;

import pl.edu.agh.marcskow.jpa.server.Server;
import pl.edu.agh.marcskow.jpa.server.implementation.FtpServerContextImpl;

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
