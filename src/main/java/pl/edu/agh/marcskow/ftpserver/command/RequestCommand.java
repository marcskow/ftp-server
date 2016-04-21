package pl.edu.agh.marcskow.ftpserver.command;

import lombok.Value;

@Value
public class RequestCommand {
    private final String title;
    private final String argument;
}

