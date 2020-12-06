package Kata.gathering.exceptions;

public class CoinNotAcceptedException extends RuntimeException {

    private String message;

    public CoinNotAcceptedException(String msg){
        super(msg);
        this.message = msg;
    }
}
