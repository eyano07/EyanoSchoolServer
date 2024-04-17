package io.eyano.eyanoschool.feesservice.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdNotFoundException extends Exception {
    public IdNotFoundException() {
        super("Identifier not found in the database");
    }
}
