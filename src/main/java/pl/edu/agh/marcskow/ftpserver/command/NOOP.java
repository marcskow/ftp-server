package pl.edu.agh.marcskow.ftpserver.command;

import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.ftpserver.clientHandler.Session;
import pl.edu.agh.marcskow.ftpserver.util.Message;

import java.io.IOException;

/**
 * NOOP command. Do nothing but keep connection up.
 */
@Slf4j
public class NOOP implements Command {
    private final Session session;
    private final Message body;

    public NOOP(Session session, Message body){
        this.session = session;
        this.body = body;
    }

    @Override
    public void execute() throws IOException{
        session.write("200 Command successful");
    }
}
