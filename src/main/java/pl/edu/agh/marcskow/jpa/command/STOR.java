package pl.edu.agh.marcskow.jpa.command;

import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.jpa.clientHandler.FtpSession;
import pl.edu.agh.marcskow.jpa.server.Server;
import pl.edu.agh.marcskow.jpa.util.Message;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class STOR extends PassiveCommand {
    private FtpSession session;
    private Message body;
    private ServerSocket serverSocket;

    public STOR(FtpSession session, Message body){
        this.session = session;
        this.body = body;
        this.serverSocket = session.getPassive().getServerSocket();
    }

    @Override
    public void execute() throws IOException {
        storFile();
    }

    public void storFile() throws IOException {
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
            try {
                InputStream in = socket.getInputStream();
                OutputStream out = new FileOutputStream(session.getRootDirectory() + "/" + filename);
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
