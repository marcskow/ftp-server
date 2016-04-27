package pl.edu.agh.marcskow.ftpserver.command;

import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.ftpserver.clientHandler.FtpSession;
import pl.edu.agh.marcskow.ftpserver.clientHandler.Session;
import pl.edu.agh.marcskow.ftpserver.util.Message;

import java.io.File;
import java.io.IOException;

/**
 * Implementation of "MKD directory" ftp command. Make new directory.
 */
@Slf4j
public class MKD implements Command {
    private final Session session;
    private final Message body;

    public MKD(Session session, Message body){
        this.session = session;
        this.body = body;
    }

    public void execute() throws IOException {
        String directory = body.getArgument(0);
        String path = session.getRootDirectory() + "/" + directory;

        makeDirectory(path);
    }

    private void makeDirectory(String path) {
        Thread t = new Thread(() -> {
            boolean result;
            File directory = new File(path);
            if (!directory.exists()) {
                result = directory.mkdir();
                try {
                    if (result) {
                        session.write("257 " + path + " was created");
                    }
                    else {
                        session.write("402 " + path + " creation unsuccessful");
                    }
                }
                catch (IOException e){
                    log.error("Error in directory creation ", e);
                }
            }
        });
        t.start();
    }
}
