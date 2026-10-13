package Kata.gathering.exceptions;

public class CoinNotAcceptedException extends RuntimeException {

    private static final long serialVersionUID = 1L;
	private String message;

    public CoinNotAcceptedException(String msg){
        super(msg);
        this.message = msg;
    }
    
    public String getMessage() {
    	return message;
    }
}
