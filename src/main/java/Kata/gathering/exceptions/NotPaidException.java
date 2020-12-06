package Kata.gathering.exceptions;

public class NotPaidException extends RuntimeException {

    private String message;

    public NotPaidException(String msg){
        super(msg);
        this.message = msg;
    }
}