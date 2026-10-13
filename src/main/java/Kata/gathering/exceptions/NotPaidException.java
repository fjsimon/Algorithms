package Kata.gathering.exceptions;

public class NotPaidException extends RuntimeException {

    private static final long serialVersionUID = 1L;
	private String message;

    public NotPaidException(String msg){
        super(msg);
        this.message = msg;
    }
    
    public String getMessage() {
    	return message;
    }
}