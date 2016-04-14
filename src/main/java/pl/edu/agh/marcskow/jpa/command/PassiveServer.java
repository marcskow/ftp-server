package pl.edu.agh.marcskow.jpa.command;

import lombok.Data;

import java.net.ServerSocket;

@Data
public class PassiveServer {
    private ServerSocket serverSocket;
    private int port;

    public PassiveServer(ServerSocket serverSocket, int port){
        this.serverSocket = serverSocket;
        this.port = port;
    }
}
