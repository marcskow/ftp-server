package pl.edu.agh.marcskow.ftpserver.command;

import pl.edu.agh.marcskow.ftpserver.clientHandler.Session;
import pl.edu.agh.marcskow.ftpserver.util.Message;

import java.io.IOException;


public class USER implements Command {
    private Session session;
    private Message body;

    public USER(Session session, Message body){
        this.session = session;
        this.body = body;
    }

    @Override
    public void execute() throws IOException {
        if(body.getArgs().length != 0) {
            String login = body.getArgument(0);
            session.setUserLogin(login);
            session.setNeededCommand("PASS");

            session.write("331 Password required");
        }
        session.write("503 Bad sequence of commands");
    }
}
