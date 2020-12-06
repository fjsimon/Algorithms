package Kata.gathering.exceptions;

public class ItemNotSelectedException extends RuntimeException {

    private String message;

    public ItemNotSelectedException(String msg){
        super(msg);
        this.message = msg;
    }
}