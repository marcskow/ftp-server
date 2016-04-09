package pl.edu.agh.marcskow.jpa.command;

import pl.edu.agh.marcskow.jpa.clientHandler.Session;
import pl.edu.agh.marcskow.jpa.util.Message;


public class USER extends AbstractCommand{
    private Session session;
    private Message body;

    public USER(Session session, Message body){
        this.session = session;
        this.body = body;
    }

    @Override
    public String execute() {
        if(body.getArgs().length != 0) {
            String login = body.getArgument(0);
            session.setUserLogin(login);
            session.setNeededCommand("PASS");

            return "331 Password required";
        }
        return "503 Bad sequence of commands";
    }
}
