package com.AristoPets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Animal")
public class AnimalNotFoundException extends Exception {
    public AnimalNotFoundException(String msg){
        super(msg);
    }
}
