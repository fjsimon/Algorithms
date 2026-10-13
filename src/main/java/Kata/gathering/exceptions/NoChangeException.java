package Kata.gathering.exceptions;

public class NoChangeException extends RuntimeException {

    private static final long serialVersionUID = 1L;
	private String message;

    public NoChangeException(String msg){
        super(msg);
        this.message = msg;
    }
    
    public String getMessage() {
    	return message;
    }
}