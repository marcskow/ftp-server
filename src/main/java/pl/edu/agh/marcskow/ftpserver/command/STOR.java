package pl.edu.agh.marcskow.ftpserver.command;

import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.ftpserver.clientHandler.Session;
import pl.edu.agh.marcskow.ftpserver.util.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Implementation of "STOR file" command. Receive a file from client and save it.
 */
@Slf4j
public class STOR implements Command {
    private final Session session;
    private final Message body;
    private final ServerSocket serverSocket;

    public STOR(Session session, Message body){
        this.session = session;
        this.body = body;
        this.serverSocket = session.getPassiveServerSocket().getServerSocket();
    }

    @Override
    public void execute() throws IOException {
        storFile();
    }

    private void storFile() throws IOException {
        String filename = body.getArgument(0);

        Thread t = new Thread(() -> {
            try {
                session.write("150 FILE: " + filename);
            }
            catch (IOException e){
                log.error("error ", e);
            }
        });
        t.start();

        Socket socket = serverSocket.accept();

        Thread t2 = new Thread(() -> {
            File file = new File(session.getRootDirectory() + "/" + filename);
            boolean append = body.getTitle().equals("APPE");
            try {
                InputStream in = socket.getInputStream();
                OutputStream out = new FileOutputStream(file, append);
                byte[] buf = new byte[8192];
                int len = 0;
                while ((len = in.read(buf)) != -1){
                    out.write(buf,0,len);
                }
                session.write("226 Transfer complete");

                // TODO: 14.04.16 think about safety of this
                socket.close();
                serverSocket.close();
            }
            catch (IOException e){
                log.error("Error in stor ", e);
            }
        });
        t2.start();
    }

}
