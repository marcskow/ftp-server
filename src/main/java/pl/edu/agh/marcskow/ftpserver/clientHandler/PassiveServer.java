package pl.edu.agh.marcskow.ftpserver.clientHandler;

import lombok.*;

import java.net.ServerSocket;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassiveServer {
    private ServerSocket serverSocket;
    private int port;
}
