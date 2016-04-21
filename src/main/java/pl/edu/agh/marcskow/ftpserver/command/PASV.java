package pl.edu.agh.marcskow.ftpserver.command;


import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.ftpserver.clientHandler.FtpSession;
import pl.edu.agh.marcskow.ftpserver.util.Message;

import java.io.IOException;
import java.net.ServerSocket;

@Slf4j
public class PASV implements Command {
    private FtpSession session;
    private Message body;

    public PASV(FtpSession session, Message body){
        this.session = session;
        this.body = body;
    }

    public void execute() throws IOException {
        PassiveServer passiveServer = create();

        session.setPassive(passiveServer);

        session.write("227 Entering Passive Mode 127.0.0.1 " + passiveServer.getPort());
    }

    public PassiveServer create() throws IOException{
        for(int port = 1000; port < 65000; port++){
            try{
                ServerSocket serverSocket = new ServerSocket(port);
                return new PassiveServer(serverSocket,port);
            }
            catch (IOException e){
                continue;
            }
        }
        throw new IOException("No free port found");
    }
}
