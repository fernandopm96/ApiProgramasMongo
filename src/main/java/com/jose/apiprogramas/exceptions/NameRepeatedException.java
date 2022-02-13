package com.jose.apiprogramas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class NameRepeatedException extends RuntimeException{
    public NameRepeatedException() {
        super("name can´t be duplicated");
    }
}
