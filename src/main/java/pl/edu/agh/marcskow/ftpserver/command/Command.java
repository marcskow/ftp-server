package pl.edu.agh.marcskow.ftpserver.command;

import java.io.IOException;

public interface Command {
    void execute() throws IOException;
}
