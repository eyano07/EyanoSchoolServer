package io.eyano.eyanoschool.feesservice.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdNotFoundParamException extends Exception {
    public IdNotFoundParamException(String message) {
        super("Identifier not found in the database : "+ message);
        log.error("Identifier not found in the database");
    }
}
