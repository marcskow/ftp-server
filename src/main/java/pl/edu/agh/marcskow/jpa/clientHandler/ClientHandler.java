package pl.edu.agh.marcskow.jpa.clientHandler;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

@Slf4j
public class ClientHandler implements Runnable {
    private final static String OPENING_MESSAGE = "Hello there. It's Brownie. Welcome !";

    private Socket clientSocket;
    private FtpSession session;
    private boolean listening = true;

    public ClientHandler(Socket client){
        clientSocket = client;
        session = new FtpSession(client);
    }

    @Override
    public void run() {
        try {
            session.startSession();
            session.write(FtpSession.WELCOME_MESSAGE);

            while (listening){
                session.handleRequestIfReceived();
                listening = session.isUp();
            }

            session.closeConnection();
        }
        catch (IOException e){
            log.error("Connection with client failed ", e);
        }
    }

}
