package pl.edu.agh.marcskow.jpa.clientHandler;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.jpa.client.Client;
import pl.edu.agh.marcskow.jpa.command.*;
import pl.edu.agh.marcskow.jpa.exceptions.InvalidMessageException;
import pl.edu.agh.marcskow.jpa.server.Server;
import pl.edu.agh.marcskow.jpa.util.Message;
import pl.edu.agh.marcskow.jpa.util.Parser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class FtpSession implements Session {
    public static final String WELCOME_MESSAGE = "Hi, it's Brownie, welcome !";
    @Getter @Setter
    private String rootDirectory = "/home/intenso/ftpServer/";
    @Getter @Setter
    private PassiveServer passive;
    @Getter @Setter
    private Socket clientSocket;
    private boolean isUp;
    @Getter @Setter
    private Client client;
    @Getter @Setter
    private PrintWriter out;
    @Getter @Setter
    private BufferedReader in;
    @Getter @Setter
    private String commandShouldBe;
    @Getter @Setter
    private String lastCommand = "USER";
    @Getter @Setter
    private int passivePort;

    public FtpSession(Socket socket){
        clientSocket = socket;
    }

    @Override
    public void startSession() throws IOException {
        isUp = true;
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        client = new Client();
    }

    @Override
    public boolean isLoggedIn() {
        return client.isLoggedIn();
    }

    @Override
    public boolean isUp() {
        return isUp;
    }

    @Override
    public void closeConnection() throws IOException {
        clientSocket.close();
    }

    @Override
    public void write(String message) throws IOException {
        out.println(message);
    }

    @Override
    public String read() throws IOException {
        return in.readLine();
    }

    @Override
    public void handleRequestIfReceived() throws IOException {
        String request = read();
        if(request.equals("")) return;

        try {
            Message message = Parser.parseMessage(request);

            log.info("REQ: " + message);

            switch (message.getTitle()){
                case "USER": write(new USER(this,message).execute()); break;
                case "PASS": write(new PASS(this,message).execute()); break;
                case "QUIT": break;
                case "NOOP": break;
                case "PASV": write(new PASV(this,message).execute()); break;
                case "STOR": new STOR(this,message).execute(); break;
                case "RETR": new RETR(this,message).execute(); break;
                case "APPE": break;
                case "ABOR": break;
                case "DELE": break;
                case "RMD": break;
                case "MKD": break;
                case "PWD": break;
                case "LIST": break;
                case "CWD": break;
                case "CHMOD": break;
                default: write("503 Bad sequence of commands ");
            }
        }
        catch (InvalidMessageException | IOException e){
            throw new IOException(e.getMessage(), e);
        }

        lastCommand = request;
    }



    @Override
    public void setUserLogin(String login) {
        client.setUsername(login);
    }

    @Override
    public String getUserLogin() {
        return client.getUsername();
    }

    @Override
    public void setUserPassword(String password) {
        client.setPassword(password);
    }

    @Override
    public String getUserPassword() {
        return client.getPassword();
    }

    @Override
    public void setNeededCommand(String command) {
        commandShouldBe = command;
    }

    @Override
    public String getNeededCommand() {
        return commandShouldBe;
    }

    @Override
    public void setIsLoggedIn(boolean isLoggedIn) {
        client.setLoggedIn(isLoggedIn);
    }
}
