package pl.edu.agh.marcskow.ftpserver.clientHandler;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.ftpserver.command.Command;
import pl.edu.agh.marcskow.ftpserver.command.CommandFactory;
import pl.edu.agh.marcskow.ftpserver.command.CommandFactoryImpl;
import pl.edu.agh.marcskow.ftpserver.command.Commands;
import pl.edu.agh.marcskow.ftpserver.exceptions.InvalidMessageException;
import pl.edu.agh.marcskow.ftpserver.server.ftpServer.FtpServerContext;
import pl.edu.agh.marcskow.ftpserver.util.Message;
import pl.edu.agh.marcskow.ftpserver.util.Parser;

import java.io.*;
import java.net.Socket;

/**
 * Session implementation.
 * @see Session
 */
@Slf4j
@Getter @Setter
public class FtpSession implements Session {
    private final Socket clientSocket;
    private PassiveServer passiveServerSocket;
    private String rootDirectory;
    private String lastCommand = "";
    private Client client;
    private PrintWriter out;
    private BufferedReader in;
    private Timer timer;
    private boolean isUp;

    public FtpSession(Socket socket, FtpServerContext context){
        clientSocket = socket;
        rootDirectory = context.getRoot();
    }

    @Override
    public void startSession() throws IOException {
        isUp = true;
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        client = new Client();
    }

    @Override
    public void handleRequestIfReceived() throws IOException {
        timer.reset();

        String request = read();
        if(request.equals("")) return;
        Message message;

        try {
            message = Parser.parseMessage(request);
            String title = message.getTitle();

            CommandFactory commandFactory = new CommandFactoryImpl();
            Command command = commandFactory.getCommand(this, message);

            if(Commands.isPassive(title) && !lastCommand.equals("PASV")){
                write("503 Bad sequence of commands ");
            } else if (Commands.needAuthorization(title) && !getIsLoggedIn()){
                write("410 Invalid login or password");
            } else {
                command.execute();
            }

            log.info("REQ: " + message);
        }
        catch (InvalidMessageException  e){
            throw new IOException(e.getMessage(), e);
        }

        lastCommand = message.getTitle().equals("") ? "" : message.getTitle();
        timer.reset();
    }

    @Override
    public void closeConnection() throws IOException {
        clientSocket.close();
        out.close();
        in.close();
        isUp = false;
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
    public void listenForActivity(){
        timer = new Timer(60000, this);
        timer.start();
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
    public void setIsLoggedIn(boolean isLoggedIn) {
        client.setLoggedIn(isLoggedIn);
    }

    @Override
    public boolean getIsLoggedIn() {
        return client.isLoggedIn();
    }
}
