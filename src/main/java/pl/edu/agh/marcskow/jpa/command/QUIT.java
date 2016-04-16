package pl.edu.agh.marcskow.jpa.command;


import pl.edu.agh.marcskow.jpa.clientHandler.FtpSession;
import pl.edu.agh.marcskow.jpa.util.Message;

public class QUIT extends ActiveCommand{
    private FtpSession session;
    private Message body;

    public QUIT(FtpSession session, Message body){
        this.session = session;
        this.body = body;
    }

    @Override
    public String execute() {
        return "221 Bye";
    }
}
