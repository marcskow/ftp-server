package pl.edu.agh.marcskow.ftpserver.command;

import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.ftpserver.clientHandler.FtpSession;
import pl.edu.agh.marcskow.ftpserver.clientHandler.Session;
import pl.edu.agh.marcskow.ftpserver.util.Message;

import java.io.IOException;


@Slf4j
public class PWD implements Command {
    private Session session;
    private Message body;

    public PWD(Session session, Message body){
        this.session = session;
        this.body = body;
    }

    public void execute() throws IOException {
        String currentDirectory = session.getRootDirectory();

        session.write("257 \"" + currentDirectory + "\" is current directory");
    }
}
