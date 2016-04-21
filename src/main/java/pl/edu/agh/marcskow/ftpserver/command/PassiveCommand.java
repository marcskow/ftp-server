package pl.edu.agh.marcskow.ftpserver.command;

import java.io.IOException;

/**
 * Created by intenso on 14.04.16.
 */
public abstract class PassiveCommand extends AbstractCommand{
    public abstract void execute() throws IOException;
}
