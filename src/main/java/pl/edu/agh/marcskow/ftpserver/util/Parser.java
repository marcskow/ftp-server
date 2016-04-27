package pl.edu.agh.marcskow.ftpserver.util;

import pl.edu.agh.marcskow.ftpserver.exceptions.InvalidMessageException;

import java.util.Arrays;

/**
 * Helper class to parse message from String to the Message class
 * @see Message
 */
public class Parser {
    /**
     * Method that parse message
     * @param message request from client
     * @return parsed message
     * @throws InvalidMessageException
     */
    public static Message parseMessage(String message) throws InvalidMessageException {
        String[] elements = message.split(" ");

        if (elements.length == 0){
            throw new InvalidMessageException("Cannot parse message with length equals 0");
        }

        Message result = new Message();
        result.setLength(elements.length - 1);
        result.setTitle(elements[0]);
        result.setArgs(Arrays.copyOfRange(elements,1,elements.length + 1));

        return result;
    }
}
