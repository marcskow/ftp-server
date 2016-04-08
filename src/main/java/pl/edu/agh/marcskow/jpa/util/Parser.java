package pl.edu.agh.marcskow.jpa.util;

import pl.edu.agh.marcskow.jpa.exceptions.InvalidMessageException;

import java.util.Arrays;

public class Parser {
    public static Message parseMessage(String message) throws InvalidMessageException {
        String[] elements = message.split(" ");

        if (elements.length == 0){
            throw new InvalidMessageException("Cannot parse message with length equals 0");
        }

        Message result = new Message();
        result.setLength(elements.length);
        result.setTitle(elements[0]);
        result.setArgs(Arrays.copyOfRange(elements,1,elements.length));

        return result;
    }
}
