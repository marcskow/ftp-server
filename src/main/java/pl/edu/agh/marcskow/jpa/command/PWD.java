package pl.edu.agh.marcskow.jpa.command;

import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.jpa.clientHandler.FtpSession;
import pl.edu.agh.marcskow.jpa.util.Message;


@Slf4j
public class PWD extends ActiveCommand {
    private FtpSession session;
    private Message body;

    public PWD(FtpSession session, Message body){
        this.session = session;
        this.body = body;
    }

    public String execute() {
        String currentDirectory = session.getRootDirectory();

        return "257 \"" + currentDirectory + "\" is current directory";
    }
}
