package pl.edu.agh.marcskow.ftpserver.command;

import pl.edu.agh.marcskow.ftpserver.clientHandler.FtpSession;
import pl.edu.agh.marcskow.ftpserver.util.Message;

import java.io.File;
import java.io.IOException;


public class CWD implements Command {
    private FtpSession session;
    private Message body;

    public CWD(FtpSession session, Message body){
        this.session = session;
        this.body = body;
    }

    public void execute() throws IOException {
        String path = body.getArgument(0);
        File file = new File(session.getRoot() + "/" + path);

        if(file.isDirectory()){
            if(path.equals("root")){
                session.setRootDirectory(session.getRoot());
            }
            else{
                session.setRootDirectory(session.getRoot() + "/" + path);
            }
            session.write("250 CWD was successful");
        }
        else {
            session.write("403 CWD was unsuccessful");
        }
    }

}
