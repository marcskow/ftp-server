package pl.edu.agh.marcskow.ftpserver.command;

import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.ftpserver.clientHandler.FtpSession;
import pl.edu.agh.marcskow.ftpserver.util.Message;

import java.io.IOException;


@Slf4j
public class NOOP implements Command {
    private FtpSession session;
    private Message body;

    public NOOP(FtpSession session, Message body){
        this.session = session;
        this.body = body;
    }

    @Override
    public void execute() throws IOException{
        session.write("200 Command successful");
    }
}
