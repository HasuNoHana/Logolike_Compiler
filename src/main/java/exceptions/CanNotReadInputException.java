package exceptions;

public class CanNotReadInputException extends RuntimeException{
    public CanNotReadInputException(String message,Exception e) {
        super(message,e);
    }
}
