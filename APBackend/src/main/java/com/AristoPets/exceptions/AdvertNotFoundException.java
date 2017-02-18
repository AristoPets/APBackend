package com.AristoPets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Advert")
public class AdvertNotFoundException extends Exception {
    public AdvertNotFoundException(String msg){
        super(msg);
    }
}
