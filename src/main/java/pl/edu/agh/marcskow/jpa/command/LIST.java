package pl.edu.agh.marcskow.jpa.command;

import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.jpa.clientHandler.FtpSession;
import pl.edu.agh.marcskow.jpa.util.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;


@Slf4j
public class LIST extends PassiveCommand {
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
                LinkedList<String> files = listFiles(new File(session.getRootDirectory()));
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

    private LinkedList<String> listFiles(File currentDirectory){
        File[] filesList = currentDirectory.listFiles();
        LinkedList<String> files = new LinkedList<>();

        if(filesList == null){
            return null;
        }

        for(File f : filesList){
            if(f.isDirectory())
                files.add(f.getName() + " DIR " + f.length());
            else if(f.isFile()){
                files.add(f.getName() + " FILE " + f.length());
            }
        }

        return files;
    }
}