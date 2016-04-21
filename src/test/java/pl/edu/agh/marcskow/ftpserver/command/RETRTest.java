package pl.edu.agh.marcskow.ftpserver.command;

import org.junit.Test;
import pl.edu.agh.marcskow.ftpserver.server.Server;
import pl.edu.agh.marcskow.server.TestFtpServerContext;

import java.net.Socket;

/**
 * Created by intenso on 14.04.16.
 */
public class RETRTest {

    @Test
    public void testSendFile() throws Exception {
        TestFtpServerContext ftpServerContext = new TestFtpServerContext();
        Server server = new Server(ftpServerContext);
        Thread thread = new Thread(server);
        thread.start();

        Socket socket = new Socket("127.0.0.1", ftpServerContext.getPort());


    }
}