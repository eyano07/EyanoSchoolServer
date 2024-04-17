package io.eyano.eyanoschool.feesservice.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdIsNullException extends Exception{
    public IdIsNullException(String message) {
        super(message);
        log.error(message);
    }
}
