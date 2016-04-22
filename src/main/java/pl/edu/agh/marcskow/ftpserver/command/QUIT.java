package pl.edu.agh.marcskow.ftpserver.command;


import pl.edu.agh.marcskow.ftpserver.clientHandler.FtpSession;
import pl.edu.agh.marcskow.ftpserver.clientHandler.Session;
import pl.edu.agh.marcskow.ftpserver.util.Message;

import java.io.IOException;

public class QUIT implements Command {
    private Session session;
    private Message body;

    public QUIT(Session session, Message body){
        this.session = session;
        this.body = body;
    }

    @Override
    public void execute() throws IOException {
        session.write("221 Bye");
    }
}
