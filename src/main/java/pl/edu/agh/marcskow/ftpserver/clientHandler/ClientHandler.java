package pl.edu.agh.marcskow.ftpserver.clientHandler;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

@Slf4j
public class ClientHandler implements Runnable {
    private final static String OPENING_MESSAGE = "Hello there. It's Brownie. Welcome !";

    private Socket clientSocket;
    private FtpSession session;

    public ClientHandler(Socket client){
        clientSocket = client;
        session = new FtpSession(client);
    }

    @Override
    public void run() {
        try {
            session.startSession();
            session.listenForActivity();

            while (session.isUp()){
                session.handleRequestIfReceived();
     //           Thread.sleep(500);
            }
            System.out.println("Wychodze");
            session.closeConnection();
        }
        catch (IOException e){  //| InterruptedException e){
            log.error("Connection with client failed ", e);
        }
        finally {
            try {
                session.closeConnection();
            } catch (IOException e){
                log.error("Closing connection failed ", e);
            }
        }
    }

}
