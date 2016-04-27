package pl.edu.agh.marcskow.ftpserver.server.implementation;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import pl.edu.agh.marcskow.ftpserver.clientHandler.ClientHandler;
import pl.edu.agh.marcskow.ftpserver.server.ftpServer.FtpServer;
import pl.edu.agh.marcskow.ftpserver.server.ftpServer.FtpServerContext;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * FtpServer implementation.
 * It's the default implementation of the server.
 * FtpServerContext contains max amount of thread pool, server root folder and port.
 * @see FtpServer
 */
@Slf4j
public class Server implements FtpServer, Runnable {
    private FtpServerContext context;

    @Getter @Setter
    private boolean isRunning;

    /**
     * The only one available constructor.
     * @param context is server context contains max amount of thread pool, server root folder and port.
     */
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
                pool.execute(new ClientHandler(serverSocket.accept(), context));
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
