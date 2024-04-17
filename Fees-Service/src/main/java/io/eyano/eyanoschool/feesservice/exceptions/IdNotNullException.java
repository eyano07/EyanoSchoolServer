package io.eyano.eyanoschool.feesservice.exceptions;


public class IdNotNullException extends Exception{
    public IdNotNullException(String message) {
        super("Identifier must be null : "+ message);
    }
}
