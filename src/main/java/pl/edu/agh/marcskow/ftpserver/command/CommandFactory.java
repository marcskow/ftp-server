package pl.edu.agh.marcskow.ftpserver.command;

import pl.edu.agh.marcskow.ftpserver.clientHandler.Session;
import pl.edu.agh.marcskow.ftpserver.util.Message;

/**
 * Factory pattern.
 * getCommand method is used to get matching command from protocol.
 */
public interface CommandFactory {
    Command getCommand(Session session, Message message);
}
