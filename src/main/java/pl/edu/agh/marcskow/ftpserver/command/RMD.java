package pl.edu.agh.marcskow.ftpserver.command;

import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.ftpserver.clientHandler.FtpSession;
import pl.edu.agh.marcskow.ftpserver.util.Message;

import java.io.File;
import java.io.IOException;

/**
 * Created by intenso on 16.04.16.
 */
@Slf4j
public class RMD implements Command {
    private FtpSession session;
    private Message body;

    public RMD(FtpSession session, Message body){
        this.session = session;
        this.body = body;
    }

    public void execute() throws IOException {
        String directory = body.getArgument(0);
        String path = session.getRootDirectory() + "/" + directory;

        deleteFile(path);
    }

    public void deleteFile(String path) {
        Thread t = new Thread(() -> {
            try {
                File directory = new File(path);
                if (directory.isDirectory()) {
                    boolean result = directory.delete();
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
