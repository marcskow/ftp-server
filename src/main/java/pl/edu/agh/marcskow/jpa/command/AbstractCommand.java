package pl.edu.agh.marcskow.jpa.command;

import pl.edu.agh.marcskow.jpa.clientHandler.Session;

/**
 * Created by intenso on 06.04.16.
 */
public abstract class AbstractCommand implements Command {
    public abstract String execute();
}
