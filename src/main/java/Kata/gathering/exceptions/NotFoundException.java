package Kata.gathering.exceptions;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
	private String message;

    public NotFoundException(String msg){
        super(msg);
        this.message = msg;
    }
    
    public String getMessage() {
    	return message;
    }
}