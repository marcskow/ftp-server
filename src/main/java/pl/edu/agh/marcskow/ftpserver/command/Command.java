package pl.edu.agh.marcskow.ftpserver.command;

import java.io.IOException;

/**
 * Command pattern, the command is a message received from client.
 */
public interface Command {
    void execute() throws IOException;
}
