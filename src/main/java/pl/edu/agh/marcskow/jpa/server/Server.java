package pl.edu.agh.marcskow.jpa.server;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.jpa.clientHandler.ClientHandler;
import pl.edu.agh.marcskow.jpa.server.ftpServer.FtpServer;
import pl.edu.agh.marcskow.jpa.server.ftpServer.FtpServerContext;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Server implements FtpServer, Runnable {
    private FtpServerContext context;

    @Getter @Setter
    private boolean isRunning;

    public Server(FtpServerContext context) {
        this.context = context;
    }

    @Override
    public void start(){
        ExecutorService pool = Executors.newFixedThreadPool(context.getThreadPool());

        try {
            log.info("Session started. Server run on ip 127.0.0.1 port " + context.getPort());

            ServerSocket serverSocket = new ServerSocket(context.getPort());

            while (isRunning) {
                pool.execute(new ClientHandler(serverSocket.accept()));
            }
        }
        catch (IOException e){
            log.error("Server error ", e);
            pool.shutdown();
        }
    }

    @Override
    public void run() {
        isRunning = true;
        start();
    }

    @Override
    public void stop() {
        isRunning = false;
    }
}
