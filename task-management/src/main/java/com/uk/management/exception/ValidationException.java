package com.uk.management.exception;

import com.uk.management.error.ErrorCode;
import com.uk.management.exception.base.InvalidInputException;
import org.springframework.http.HttpStatus;

public class ValidationException extends InvalidInputException {

    private final String[] messageArgs;

    private final HttpStatus httpStatus;


    public ValidationException(String exceptionCode) {
        super( exceptionCode);
        this.messageArgs=null;
        this.httpStatus=null;
    }

    public ValidationException(ErrorCode errorCode, String exceptionCode) {
        super(errorCode, exceptionCode);
        this.messageArgs=null;
        this.httpStatus=null;
    }

    public ValidationException(String message, ErrorCode errorCode, String exceptionCode) {
        super(message, errorCode, exceptionCode);
        this.messageArgs=null;
        this.httpStatus=null;
    }

    public ValidationException(String message, Throwable cause, ErrorCode errorCode, String exceptionCode) {
        super(message, cause, errorCode, exceptionCode);
        this.messageArgs=null;
        this.httpStatus=null;
    }

    public ValidationException(Throwable cause, ErrorCode errorCode, String exceptionCode) {
        super(cause, errorCode, exceptionCode);
        this.messageArgs=null;
        this.httpStatus=null;
    }

    public String[] getMessageArgs(){return this.messageArgs;}

    public HttpStatus getHttpStatus(){return this.httpStatus;}
}
