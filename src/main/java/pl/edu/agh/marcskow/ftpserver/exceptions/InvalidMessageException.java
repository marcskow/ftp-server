package pl.edu.agh.marcskow.ftpserver.exceptions;

public class InvalidMessageException extends Exception {
    public InvalidMessageException(String message){
        super(message);
    }

    public InvalidMessageException(Throwable cause){
        super(cause);
    }

    public InvalidMessageException(String message, Throwable cause){
        super(message,cause);
    }
}
