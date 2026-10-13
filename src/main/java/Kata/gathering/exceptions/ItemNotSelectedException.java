package Kata.gathering.exceptions;

public class ItemNotSelectedException extends RuntimeException {

    private static final long serialVersionUID = 1L;
	private String message;

    public ItemNotSelectedException(String msg){
        super(msg);
        this.message = msg;
    }
    
    public String getMessage() {
    	return message;
    }
}