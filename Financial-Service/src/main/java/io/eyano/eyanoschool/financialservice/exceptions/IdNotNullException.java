package io.eyano.eyanoschool.financialservice.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdNotNullException extends Exception{
    public IdNotNullException(String message) {
        super("Identifier must be null : "+ message);
        log.error("Identifier must be null : "+ message);
    }
}
