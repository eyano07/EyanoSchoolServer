package io.eyano.eyanoschool.feesservice.exceptions;

public class RemoveTrueException extends Exception{
    public RemoveTrueException(String message) {
        super("Remove must be false : "+ message);
    }
    public RemoveTrueException() {
        super("Remove must be false");
    }
}
