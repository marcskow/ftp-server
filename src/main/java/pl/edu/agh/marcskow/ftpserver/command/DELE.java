package pl.edu.agh.marcskow.ftpserver.command;

import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.ftpserver.clientHandler.FtpSession;
import pl.edu.agh.marcskow.ftpserver.clientHandler.Session;
import pl.edu.agh.marcskow.ftpserver.util.Message;

import java.io.File;
import java.io.IOException;

/**
 * Delete specified file.
 */
@Slf4j
public class DELE implements Command {
    private final Session session;
    private final Message body;

    public DELE(Session session, Message body){
        this.session = session;
        this.body = body;
    }

    public void execute() throws IOException {
        String filename = body.getArgument(0);
        String path = session.getRootDirectory() + "/" + filename;

        deleteFile(path);
    }

    private void deleteFile(String path) {
        Thread t = new Thread(() -> {
            try {
                File file = new File(path);
                if (file.isFile()) {
                    boolean result = file.delete();
                    if (result) {
                        session.write("250 DELE was successful");
                    } else {
                        session.write("401 DELE unsuccessful");
                    }
                }
            }
            catch (IOException e){
                log.error("Error in DELE ", e);
            }
        });
        t.start();
    }
}
