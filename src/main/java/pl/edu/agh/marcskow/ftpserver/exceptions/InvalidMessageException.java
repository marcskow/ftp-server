package pl.edu.agh.marcskow.ftpserver.exceptions;

/**
 * This exception is thrown when the server received an invalid request
 */
public class InvalidMessageException extends Exception {
    public InvalidMessageException(String message){
        super(message);
    }
}
