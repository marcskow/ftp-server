package pl.edu.agh.marcskow.jpa.command;

import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.jpa.clientHandler.FtpSession;
import pl.edu.agh.marcskow.jpa.util.Message;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class RETR extends PassiveCommand{
    private FtpSession session;
    private Message body;
    private ServerSocket serverSocket;

    public RETR(FtpSession session, Message body){
        this.session = session;
        this.body = body;
        this.serverSocket = session.getPassive().getServerSocket();
    }

    @Override
    public void execute() throws IOException {
        sendFile();
    }

    public void sendFile() throws IOException {
        String filename = body.getArgument(0);

        Thread t = new Thread(() -> {
           try{
               session.write("150 Opening binary mode data connection for '" + filename + "'");
           }
           catch (IOException e){
               log.error("error ", e);
           }
        });
        t.start();

        Socket socket = serverSocket.accept();

        Thread t2 = new Thread(() -> {
           try(InputStream in = new FileInputStream(session.getRootDirectory() + "/" + filename);
               OutputStream out = socket.getOutputStream()){

               byte[] buf = new byte[8192];
               int len = 0;
               while ((len = in.read(buf)) != -1) {
                   out.write(buf, 0, len);
               }

               session.write("226 Transfer complete");
           }
           catch (IOException e){
               log.error("Error in retr ", e);
           }
        });
        t2.start();

    }
}
