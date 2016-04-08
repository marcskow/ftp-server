package pl.edu.agh.marcskow.jpa.clientHandler;


import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.jpa.exceptions.InvalidMessageException;
import pl.edu.agh.marcskow.jpa.util.Message;
import pl.edu.agh.marcskow.jpa.util.Parser;

import java.io.*;
import java.net.Socket;

@Slf4j
public class FtpSession implements Session {
    public static final String WELCOME_MESSAGE = "Hi, it's Brownie, welcome !";
    private String rootDirectory = "/home/intenso/ftpServer/";
    private Socket clientSocket;
    private boolean isUp;
    private boolean isLoggedIn;


    public FtpSession(Socket socket){
        clientSocket = socket;
    }

    @Override
    public void startSession() {
        isUp = true;
    }

    @Override
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    @Override
    public boolean isUp() {
        return isUp;
    }

    @Override
    public void closeConnection() {

    }

    @Override
    public void write(String message) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println(message);
    }

    @Override
    public String read() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return in.readLine();
    }

    @Override
    public void handleRequestIfReceived() throws IOException {
        String request = read();

        if(request.equals("")) return;

        try {
            Message message = Parser.parseMessage(request);

            switch (message.getTitle()){
                default: write("I got your message");
            }
        }
        catch (InvalidMessageException e){
            throw new IOException(e.getMessage(), e);
        }

    }
}
