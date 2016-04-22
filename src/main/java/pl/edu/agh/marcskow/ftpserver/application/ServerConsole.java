package pl.edu.agh.marcskow.ftpserver.application;

import pl.edu.agh.marcskow.ftpserver.server.Server;
import pl.edu.agh.marcskow.ftpserver.server.implementation.FtpServerContextImpl;


public class ServerConsole {
    public static void main(String[] args) {
        Server server = new Server(new FtpServerContextImpl());
        Thread thread = new Thread(server);
        thread.start();
    }

}
