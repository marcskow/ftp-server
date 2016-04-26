package pl.edu.agh.marcskow.ftpserver.clientHandler;

import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.ftpserver.server.ftpServer.FtpServerContext;

import java.io.*;
import java.net.Socket;

@Slf4j
public class ClientHandler implements Runnable {
    private final FtpSession session;

    public ClientHandler(Socket client, FtpServerContext context){
        session = new FtpSession(client, context);
    }

    @Override
    public void run() {
        try {
            session.startSession();
            session.listenForActivity();

            while (session.isUp()){
                session.handleRequestIfReceived();
            }
            session.closeConnection();
        }
        catch (IOException e){
            log.error("Connection with client failed ", e);
        }
        finally {
            try {
                session.closeConnection();
            } catch (IOException e){
                log.error("Closing failed ", e);
            }
        }
    }

}
