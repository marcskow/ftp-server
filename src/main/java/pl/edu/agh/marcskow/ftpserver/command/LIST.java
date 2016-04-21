package pl.edu.agh.marcskow.ftpserver.command;

import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.ftpserver.clientHandler.FtpSession;
import pl.edu.agh.marcskow.ftpserver.util.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;


@Slf4j
public class LIST implements Command {
    private FtpSession session;
    private Message body;
    private ServerSocket serverSocket;

    public LIST(FtpSession session, Message body) {
        this.session = session;
        this.body = body;
        this.serverSocket = session.getPassive().getServerSocket();
    }

    @Override
    public void execute() throws IOException {

        Thread t = new Thread(() -> {
            try {
                session.write("150 Opening ASCII mode data connection for '/bin/ls'.");
            } catch (IOException e) {
                log.error("error ", e);
            }
        });
        t.start();

        Socket socket = serverSocket.accept();

        Thread t2 = new Thread(() -> {
            try {
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                String[] files = listFiles(new File(session.getRootDirectory()));
                out.writeObject(files);

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

    private String[] listFiles(File currentDirectory){
        File[] filesList = currentDirectory.listFiles();
        LinkedList<String> files = new LinkedList<>();

        if(filesList == null){
            return null;
        }

        for(File f : filesList){
            if(f.isDirectory())
                files.add(f.getName() + "  DIR");// + f.length()); // FIXME: 16.04.16
            else if(f.isFile()){
                files.add(f.getName() + " FILE");// + f.length()); // FIXME: 16.04.16
            }
        }

        return files.toArray(new String[files.size()]);
    }
}