package io.eyano.eyanoschool.financialservice.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdNotFoundException extends Exception {
    public IdNotFoundException() {
        super("Identifier not found in the database");
        log.error("Identifier not found in the database");
    }

    public IdNotFoundException(String msg) {
        super("Identifier not found in the database : " + msg);
        log.error("Identifier not found in the database : " + msg);
    }
}
