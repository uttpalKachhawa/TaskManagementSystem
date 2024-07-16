package com.uk.management.error;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public interface ErrorCode extends Serializable {

    String errorCode();
    HttpStatus httpStatus();
}
