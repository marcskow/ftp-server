package pl.edu.agh.marcskow.jpa.command;

import lombok.Value;

@Value
public class RequestCommand {
    private final String title;
    private final String argument;
}

