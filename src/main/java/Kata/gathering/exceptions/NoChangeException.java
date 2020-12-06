package Kata.gathering.exceptions;

public class NoChangeException extends RuntimeException {

    private String message;

    public NoChangeException(String msg){
        super(msg);
        this.message = msg;
    }
}