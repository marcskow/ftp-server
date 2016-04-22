package pl.edu.agh.marcskow.ftpserver.clientHandler;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.*;

@Slf4j
public class FtpSessionTest {
    FtpSession ftpSession;
    ServerSocket serverSocket;

    PrintWriter socketWriter;
    BufferedReader socketReader;

    Socket socket;

    @Before
    public void setUp() throws Exception {
        ServerSocket serverSocket = new ServerSocket(4444);

        Thread t = new Thread(() -> {
            try {
                serverSocket.accept();
            } catch (IOException e){
                log.error("Error inside test: cannot connect to test server ", e);
            }
        });
        t.start();

        Socket socket = new Socket("127.0.0.1",4444);
       ftpSession = new FtpSession(socket);
    }


    @After
    public void tearDown() throws Exception {
        socket.close();
        serverSocket.close();
        socketWriter.close();
        socketReader.close();
    }

    @Test
    public void testIsUp() throws Exception {

    }
}