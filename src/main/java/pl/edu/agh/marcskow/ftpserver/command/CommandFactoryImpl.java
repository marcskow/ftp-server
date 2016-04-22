package pl.edu.agh.marcskow.ftpserver.command;

import pl.edu.agh.marcskow.ftpserver.clientHandler.Session;
import pl.edu.agh.marcskow.ftpserver.util.Message;

/**
 * Factory pattern implementation.
 * @see CommandFactory
 */
public class CommandFactoryImpl implements CommandFactory {

    /**
     * APPE return STOR in which there is an if in STOR choosing between APPE or STOR operation
     * CHMOD and ABOR are not implemented in this version. Returning NOOP
     * @param session ftpsession with settings and basic operations
     * @param message request message from client
     * @return Command instance
     */
    public Command getCommand(Session session, Message message) {
        String command = message.getTitle();

        switch (command){
            case "USER": return new USER(session,message);
            case "PASS": return new PASS(session,message);
            case "QUIT": return new QUIT(session,message);
            case "NOOP": return new NOOP(session,message);
            case "PASV": return new PASV(session,message);
            case "STOR": return new STOR(session,message);
            case "RETR": return new RETR(session,message);
            case "APPE": return new STOR(session,message);
            case "ABOR": return new NOOP(session,message);
            case "DELE": return new DELE(session,message);
            case "RMD":  return new RMD(session,message);
            case "MKD":  return new MKD(session,message);
            case "PWD":  return new PWD(session,message);
            case "LIST": return new LIST(session,message);
            case "CWD":  return new CWD(session,message);
            case "CHMOD":return new NOOP(session,message);
            default: return null;
        }
    }
}
