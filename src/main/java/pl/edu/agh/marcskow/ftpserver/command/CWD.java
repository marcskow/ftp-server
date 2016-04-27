package pl.edu.agh.marcskow.ftpserver.command;

import pl.edu.agh.marcskow.ftpserver.clientHandler.Session;
import pl.edu.agh.marcskow.ftpserver.util.Message;

import java.io.File;
import java.io.IOException;


/**
 * Change working directory.
 */
public class CWD implements Command {
    private final Session session;
    private final Message body;

    public CWD(Session session, Message body){
        this.session = session;
        this.body = body;
    }

    public void execute() throws IOException {
        String path = body.getArgument(0);
        File file = new File(Session.DEFAULT_ROOT_DIRECTORY + "/" + path);

        if(file.isDirectory()){
            if(path.equals("root")){
                session.setRootDirectory(Session.DEFAULT_ROOT_DIRECTORY);
            } else {
                session.setRootDirectory(Session.DEFAULT_ROOT_DIRECTORY + "/" + path);
            }
            session.write("250 CWD was successful");
        } else {
            session.write("403 CWD was unsuccessful");
        }
    }

}
