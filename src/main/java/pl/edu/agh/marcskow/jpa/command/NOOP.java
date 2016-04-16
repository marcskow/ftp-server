package pl.edu.agh.marcskow.jpa.command;

import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.jpa.clientHandler.FtpSession;
import pl.edu.agh.marcskow.jpa.util.Message;


@Slf4j
public class NOOP extends ActiveCommand {
    private FtpSession session;
    private Message body;

    public NOOP(FtpSession session, Message body){
        this.session = session;
        this.body = body;
    }

    @Override
    public String execute() {
        return "200 Command succesful";
    }
}
